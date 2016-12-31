package nju.course.dao;

/**
 * Created by Mr.Zero on 2016/12/30.
 */
public interface AuthDao {
    /**
     * login system
     * @param username
     * @param password
     * @return student_id if login success, null if login fail
     */
    public Integer login(String username, String password);

}
