package nju.course.servlets;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Mr.Zero on 2016/12/13.
 */
@WebServlet(name = "login", urlPatterns = "/login", loadOnStartup = 1)
public class Login extends HttpServlet {

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
        this.getServletContext().setAttribute("userCount", 0);
        this.getServletContext().setAttribute("loginedCount", 0);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("student_id") != null) {
            resp.sendRedirect("/home");
        }
        else {
            req.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String) req.getParameter("username");
        String password = (String) req.getParameter("password");

        String sql = "SELECT id FROM student WHERE name = ? AND password = ? LIMIT 1 ";
        //use try with resources
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, name);
            statement.setString(2, password);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()){
                    int student_id = result.getInt("id");
                    HttpSession session = req.getSession();
                    session.setAttribute("student_id", student_id);
                    ServletContext application = session.getServletContext();
                    application.setAttribute("loginedCount", (Integer)application.getAttribute("loginedCount") +1);
                    resp.sendRedirect("/home");
                }
                else {          //login fail
                    req.setAttribute("last_name", name);
                    req.setAttribute("login_failed", true);
                    req.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(req, resp);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
