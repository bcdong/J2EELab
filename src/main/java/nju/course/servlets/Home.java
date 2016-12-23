package nju.course.servlets;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Mr.Zero on 2016/12/20.
 */
@WebServlet(name = "home", urlPatterns = "/home", loadOnStartup = 1)
public class Home extends HttpServlet {

    private DataSource dataSource = null;

    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();
        properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
        properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
        try {
            InitialContext context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/course");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("student_id") == null) {
            req.getRequestDispatcher("/WEB-INF/jsp/view/guestHome.jsp").forward(req, resp);
        }
        else {
            int student_id = (Integer) session.getAttribute("student_id");
            String username = "";
            ArrayList<String> courseList = new ArrayList<String>();
            ArrayList<String> absentExamList = new ArrayList<String>();

            String sql1 = "SELECT name FROM student WHERE id = ? ";
            String sql2 = "SELECT course.name FROM major, course WHERE major.student_id = ? AND major.course_id = course.id ";
            String sql3 = "SELECT exam_name FROM exam WHERE exam.course_id IN (SELECT major.course_id FROM major WHERE major.student_id = ? ) AND exam.id NOT IN (SELECT t1.exam_id FROM exam_score t1 WHERE t1.student_id = ? AND t1.score IS NOT NULL )";
            try (Connection conn = dataSource.getConnection();
                 PreparedStatement nameMajorStatement = conn.prepareStatement(sql1);
                 PreparedStatement majorStatement = conn.prepareStatement(sql2);
                 PreparedStatement checkStatement = conn.prepareStatement(sql3))
            {
                nameMajorStatement.setInt(1, student_id);
                majorStatement.setInt(1, student_id);
                checkStatement.setInt(1, student_id);
                checkStatement.setInt(2, student_id);

                try (ResultSet result1 = nameMajorStatement.executeQuery();
                     ResultSet result2 = majorStatement.executeQuery();
                     ResultSet result3 = checkStatement.executeQuery())
                {
                    if (result1.next()){
                        username = result1.getString("name");
                    }
                    else {}
                    while (result2.next()) {
                        courseList.add(result2.getString("name"));
                    }
                    while (result3.next()) {
                        absentExamList.add(result3.getString("exam_name"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            req.setAttribute("username", username);
            req.setAttribute("courseList", courseList);
            req.setAttribute("absentExamList", absentExamList);
            req.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
