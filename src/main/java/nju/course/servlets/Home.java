package nju.course.servlets;

import nju.course.factory.ModelFactory;
import nju.course.model.HomeModel;
import nju.course.model.impl.HomeModelImpl;

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
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Mr.Zero on 2016/12/20.
 */
@WebServlet(name = "home", urlPatterns = "/home", loadOnStartup = 1)
public class Home extends HttpServlet {

    private HomeModel homeModel = null;

    @Override
    public void init() throws ServletException {
        homeModel = ModelFactory.getHomeModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //TODO rollback to use the commented method, do not use MyTag!!! Delete <MyTag:checkSession/> in home.jsp
        /*
        if (session.getAttribute("student_id") == null) {
            req.getRequestDispatcher("/WEB-INF/jsp/view/guestHome.jsp").forward(req, resp);
        }
        else {
            int student_id = (Integer) session.getAttribute("student_id");
            homeModel.getBasicInfo(student_id, req, resp);
            req.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp").forward(req, resp);
        }
        */
        req.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
