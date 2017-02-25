package nju.course.dao.impl;

import nju.course.dao.StudentInfoDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Zero on 2016/12/30.
 */
@Repository
public class StudentInfoDaoImpl implements StudentInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public StudentInfoDaoImpl() {
    }


    @Override
    public String getName(int student_id) {
        String sql = "SELECT name FROM student WHERE id = ? ";
        String username = null;
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery(sql);
        query.setParameter(1, student_id);
        List<Object> names = query.list();
        if (names.size() > 0) {
            username = (String) names.get(0);
        }
        session.close();
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement statement = conn.prepareStatement(sql))
//        {
//            statement.setInt(1, student_id);
//            try (ResultSet result = statement.executeQuery())
//            {
//                if (result.next()){
//                    username = result.getString("name");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return username;
    }

    @Override
    public ArrayList<String> getMajorCourses(int student_id) {
        ArrayList<String> courseList = new ArrayList<>();
        String sql = "SELECT course.name FROM major, course WHERE major.student_id = ? AND major.course_id = course.id ";
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery(sql);
        query.setParameter(1, student_id);
        List<Object> courses = query.list();
        for (Object item: courses){
            courseList.add((String) item);
        }
        session.close();
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement statement = conn.prepareStatement(sql))
//        {
//            statement.setInt(1, student_id);
//
//            try (ResultSet result = statement.executeQuery())
//            {
//                while (result.next()) {
//                    courseList.add(result.getString("name"));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return courseList;
    }
}
