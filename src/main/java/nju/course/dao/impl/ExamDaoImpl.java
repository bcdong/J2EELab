package nju.course.dao.impl;

import nju.course.dao.ExamDao;
import nju.course.factory.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Mr.Zero on 2016/12/30.
 */
public class ExamDaoImpl implements ExamDao {
    private static ExamDaoImpl ourInstance = new ExamDaoImpl();

    public static ExamDaoImpl getInstance() {
        return ourInstance;
    }

    private DataSource dataSource = null;

    private ExamDaoImpl() {
        this.dataSource = DataSourceFactory.getDataSource();
    }

    @Override
    public ArrayList<String> getAbsentExams(int student_id) {
        ArrayList<String> absentExamList = new ArrayList<>();
        String sql = "SELECT exam_name FROM exam WHERE exam.course_id IN (SELECT major.course_id FROM major WHERE major.student_id = ? ) AND exam.id NOT IN (SELECT t1.exam_id FROM exam_score t1 WHERE t1.student_id = ? AND t1.score IS NOT NULL )";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql))
        {
            statement.setInt(1, student_id);
            statement.setInt(2, student_id);

            try (ResultSet result = statement.executeQuery())
            {
                while (result.next()) {
                    absentExamList.add(result.getString("exam_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absentExamList;
    }
}
