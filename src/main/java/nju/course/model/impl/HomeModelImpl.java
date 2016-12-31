package nju.course.model.impl;

import nju.course.dao.ExamDao;
import nju.course.dao.StudentInfoDao;
import nju.course.factory.DaoFactory;
import nju.course.model.HomeModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mr.Zero on 2016/12/31.
 */
public class HomeModelImpl implements HomeModel {

    private static HomeModelImpl ourInstance = new HomeModelImpl();

    public static HomeModelImpl getInstance() {
        return ourInstance;
    }

    private StudentInfoDao studentInfoDao = null;
    private ExamDao examDao = null;

    private HomeModelImpl() {
        studentInfoDao = DaoFactory.getStudentInfoDao();
        examDao = DaoFactory.getExamDao();
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
