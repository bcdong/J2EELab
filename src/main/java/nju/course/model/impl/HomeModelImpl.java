package nju.course.model.impl;

import nju.course.dao.ExamDao;
import nju.course.dao.StudentInfoDao;
import nju.course.model.HomeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Mr.Zero on 2016/12/31.
 */
@Service
public class HomeModelImpl implements HomeModel {

    @Autowired
    private StudentInfoDao studentInfoDao;

    @Autowired
    private ExamDao examDao = null;

    public HomeModelImpl() {
    }

    @Override
    public void getBasicInfo(int student_id, HttpServletRequest req, HttpServletResponse resp) {
        String username = studentInfoDao.getName(student_id);
        ArrayList<String> courseList = studentInfoDao.getMajorCourses(student_id);
        ArrayList<String> absentExamList = examDao.getAbsentExams(student_id);
        req.setAttribute("username", username);
        req.setAttribute("courseList", courseList);
        req.setAttribute("absentExamList", absentExamList);
    }
}
