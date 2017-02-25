package nju.course.dao.impl;

import nju.course.dao.ExamDao;
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
public class ExamDaoImpl implements ExamDao {

    @Autowired
    private SessionFactory sessionFactory;

    public ExamDaoImpl() {
    }

    @Override
    public ArrayList<String> getAbsentExams(int student_id) {
        ArrayList<String> absentExamList = new ArrayList<>();
        String sql = "SELECT exam_name FROM exam WHERE exam.course_id IN (SELECT major.course_id FROM major WHERE major.student_id = ? ) AND exam.id NOT IN (SELECT t1.exam_id FROM exam_score t1 WHERE t1.student_id = ? AND t1.score IS NOT NULL )";
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery(sql);
        query.setParameter(1, student_id);
        query.setParameter(2, student_id);
        List<Object> exams = query.list();
        for (Object item: exams) {
            absentExamList.add((String) item);
        }
        session.close();

//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement statement = conn.prepareStatement(sql))
//        {
//            statement.setInt(1, student_id);
//            statement.setInt(2, student_id);
//
//            try (ResultSet result = statement.executeQuery())
//            {
//                while (result.next()) {
//                    absentExamList.add(result.getString("exam_name"));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return absentExamList;
    }
}
