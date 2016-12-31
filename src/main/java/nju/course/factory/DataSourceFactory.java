package nju.course.factory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Mr.Zero on 2016/12/30.
 */
public class DataSourceFactory {

    private static DataSource ds = null;

    public static DataSource getDataSource() {
        if (ds == null) {
            initDataSource();
        }
        return ds;
    }

    private static void initDataSource() {
        Properties properties = new Properties();
        properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
        properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
        try {
            InitialContext context = new InitialContext(properties);
            ds = (DataSource) context.lookup("java:comp/env/jdbc/course");
        } catch (NamingException e) {
            System.err.println("Fatal Error: database initialize fail! ");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
