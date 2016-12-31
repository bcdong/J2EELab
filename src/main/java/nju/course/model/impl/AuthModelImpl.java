package nju.course.model.impl;

import nju.course.dao.AuthDao;
import nju.course.factory.DaoFactory;
import nju.course.model.AuthModel;

/**
 * Created by Mr.Zero on 2016/12/31.
 */
public class AuthModelImpl implements AuthModel {
    private static AuthModelImpl ourInstance = new AuthModelImpl();

    public static AuthModelImpl getInstance() {
        return ourInstance;
    }

    private AuthDao authDao = null;

    private AuthModelImpl() {
        authDao = DaoFactory.getAuthDao();
    }

    @Override
    public Integer login(String username, String password) {
        return authDao.login(username, password);
    }
}
