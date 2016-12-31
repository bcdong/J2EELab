package nju.course.factory;

import nju.course.dao.AuthDao;
import nju.course.dao.ExamDao;
import nju.course.dao.StudentInfoDao;
import nju.course.dao.impl.AuthDaoImpl;
import nju.course.dao.impl.ExamDaoImpl;
import nju.course.dao.impl.StudentInfoDaoImpl;

/**
 * Created by Mr.Zero on 2016/12/30.
 */
public class DaoFactory {

    public static AuthDao getAuthDao() {
        return AuthDaoImpl.getInstance();
    }

    public static ExamDao getExamDao() {
        return ExamDaoImpl.getInstance();
    }

    public static StudentInfoDao getStudentInfoDao() {
        return StudentInfoDaoImpl.getInstance();
    }
}
