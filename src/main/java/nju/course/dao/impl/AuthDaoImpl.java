package nju.course.dao.impl;

import nju.course.dao.AuthDao;
import nju.course.factory.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mr.Zero on 2016/12/30.
 */
public class AuthDaoImpl implements AuthDao {
    private static AuthDaoImpl ourInstance = new AuthDaoImpl();

    public static AuthDaoImpl getInstance() {
        return ourInstance;
    }

    private DataSource dataSource = null;

    private AuthDaoImpl() {
        this.dataSource = DataSourceFactory.getDataSource();
    }

    @Override
    public Integer login(String username, String password) {
        Integer student_id = null;
        String sql = "SELECT id FROM student WHERE name = ? AND password = ? LIMIT 1 ";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()){
                    student_id = result.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student_id;
    }
}
