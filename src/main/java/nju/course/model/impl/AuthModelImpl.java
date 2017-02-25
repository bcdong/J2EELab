package nju.course.model.impl;

import nju.course.dao.AuthDao;
import nju.course.model.AuthModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.Zero on 2016/12/31.
 */
@Service
public class AuthModelImpl implements AuthModel {

    @Autowired
    private AuthDao authDao = null;

    public AuthModelImpl() {
    }

    @Override
    public Integer login(String username, String password) {
        return authDao.login(username, password);
    }
}
