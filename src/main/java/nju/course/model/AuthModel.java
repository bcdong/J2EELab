package nju.course.model;

/**
 * Created by Mr.Zero on 2016/12/30.
 */
public interface AuthModel {
    /**
     * login system
     * @param username
     * @param password
     * @return student_id if login success, null if login fail
     */
    public Integer login(String username, String password);
}
