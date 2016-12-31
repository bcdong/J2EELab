package nju.course.dao.impl;

import nju.course.dao.StudentInfoDao;
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
public class StudentInfoDaoImpl implements StudentInfoDao {
    private static StudentInfoDaoImpl ourInstance = new StudentInfoDaoImpl();

    public static StudentInfoDaoImpl getInstance() {
        return ourInstance;
    }

    private DataSource dataSource = null;

    private StudentInfoDaoImpl() {
        this.dataSource = DataSourceFactory.getDataSource();
    }


    @Override
    public String getName(int student_id) {
        String sql = "SELECT name FROM student WHERE id = ? ";
        String username = null;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql))
        {
            statement.setInt(1, student_id);
            try (ResultSet result = statement.executeQuery())
            {
                if (result.next()){
                    username = result.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }

    @Override
    public ArrayList<String> getMajorCourses(int student_id) {
        ArrayList<String> courseList = new ArrayList<>();
        String sql = "SELECT course.name FROM major, course WHERE major.student_id = ? AND major.course_id = course.id ";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql))
        {
            statement.setInt(1, student_id);

            try (ResultSet result = statement.executeQuery())
            {
                while (result.next()) {
                    courseList.add(result.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }
}
