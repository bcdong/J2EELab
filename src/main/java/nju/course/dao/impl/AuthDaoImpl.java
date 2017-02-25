package nju.course.dao.impl;

import nju.course.dao.AuthDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mr.Zero on 2016/12/30.
 */
@Repository
public class AuthDaoImpl implements AuthDao {

    @Autowired
    private SessionFactory sessionFactory;

    public AuthDaoImpl() {
    }

    @Override
    public Integer login(String username, String password) {
        Integer student_id = null;
        String sql = "SELECT id FROM student WHERE name = ? AND password = ? LIMIT 1 ";
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery(sql);
        query.setParameter(1, username);
        query.setParameter(2, password);
        List<Object> results = query.list();
        if (results.size() > 0) {
            student_id = (Integer) results.get(0);
        }
        session.close();
//
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql))
//        {
//            statement.setString(1, username);
//            statement.setString(2, password);
//            try (ResultSet result = statement.executeQuery()) {
//                if (result.next()){
//                    student_id = result.getInt("id");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return student_id;
    }
}
