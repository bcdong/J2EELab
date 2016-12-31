package nju.course.factory;

import nju.course.model.AuthModel;
import nju.course.model.HomeModel;
import nju.course.model.impl.AuthModelImpl;
import nju.course.model.impl.HomeModelImpl;

/**
 * Created by Mr.Zero on 2016/12/30.
 */
public class ModelFactory {
    public static AuthModel getAuthModel() {
        return AuthModelImpl.getInstance();
    }

    public static HomeModel getHomeModel() {
        return HomeModelImpl.getInstance();
    }
}
