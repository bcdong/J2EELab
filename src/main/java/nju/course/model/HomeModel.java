package nju.course.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by Mr.Zero on 2016/12/30.
 */
public interface HomeModel {

    /**
     * get data for home page, include username, majored courses, absent exams
     * @param student_id
     */
    public void getBasicInfo(int student_id, HttpServletRequest req, HttpServletResponse resp);
}
