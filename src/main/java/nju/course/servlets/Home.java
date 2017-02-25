package nju.course.servlets;

import nju.course.model.HomeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Mr.Zero on 2016/12/20.
 */
@WebServlet(name = "home", urlPatterns = "/home", loadOnStartup = 1)
public class Home extends HttpServlet {

    private HomeModel homeModel;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        this.homeModel = (HomeModel) applicationContext.getBean("homeModel");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("student_id") == null) {
            req.getRequestDispatcher("/WEB-INF/jsp/view/guestHome.jsp").forward(req, resp);
        }
        else {
            int student_id = (Integer) session.getAttribute("student_id");
            homeModel.getBasicInfo(student_id, req, resp);
            req.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
