package nju.course.listeners;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Mr.Zero on 2016/12/23.
 */
@WebListener
public class UserCountListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext application = se.getSession().getServletContext();
        application.setAttribute("userCount", (Integer)application.getAttribute("userCount") +1);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext application = se.getSession().getServletContext();
        if (se.getSession().getAttribute("student_id") != null) {
            application.setAttribute("loginedCount", (Integer)application.getAttribute("loginedCount") -1);
        }
        else {}
        application.setAttribute("userCount", (Integer)application.getAttribute("userCount") -1);
    }
}
