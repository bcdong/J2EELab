package nju.course.servlets;

import nju.course.model.AuthModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Mr.Zero on 2016/12/13.
 */
@WebServlet(name = "login", urlPatterns = "/login", loadOnStartup = 1)
public class Login extends HttpServlet {

    private AuthModel authModel;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        this.authModel = (AuthModel) applicationContext.getBean("authModel");
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
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Integer user_id = authModel.login(username, password);

        if (user_id == null) {      //username or password error
            req.setAttribute("last_name", username);
            req.setAttribute("login_failed", true);
            req.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(req, resp);
        }
        else {
            HttpSession session = req.getSession();
            session.setAttribute("student_id", user_id);
            ServletContext application = session.getServletContext();
            application.setAttribute("loginedCount", (Integer)application.getAttribute("loginedCount") +1);
            resp.sendRedirect("/home");
        }
    }
}
