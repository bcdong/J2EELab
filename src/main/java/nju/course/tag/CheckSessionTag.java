package nju.course.tag;

import nju.course.factory.ModelFactory;
import nju.course.model.HomeModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by Mr.Zero on 2016/12/31.
 */
public class CheckSessionTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException,  IOException {
        PageContext pageContext = (PageContext) this.getJspContext();
        Object student_id_id_obj = pageContext.getSession().getAttribute("student_id");
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
        if (student_id_id_obj == null) {
            response.sendRedirect("/login");
        }
        else {
            Integer student_id = (Integer) student_id_id_obj;
            HomeModel homeModel = ModelFactory.getHomeModel();
            homeModel.getBasicInfo(student_id, request, response);
        }
    }
}
