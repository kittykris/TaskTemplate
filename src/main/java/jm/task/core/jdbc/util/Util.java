package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_JDBC_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "12345";
    private static Connection connection = makeJDBCConnection();

    private static Connection makeJDBCConnection() {
        if (connection == null) {
            try {
                Class.forName(MYSQL_DRIVER);
                connection = DriverManager.getConnection(MYSQL_JDBC_URL, LOGIN, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Problem with connection");
            }
        }
        return connection;
    }

    public static Connection getJDBCConnection() {
        return connection;
    }

    private static final String MYSQL_HIBERNATE_URL = "jdbc:mysql://localhost:3306/mydb_for_hib";
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            try {
            sessionFactory = new Configuration()
                    .setProperty("hibernate.connection.driver_class", MYSQL_DRIVER)
                    .setProperty("hibernate.connection.url", MYSQL_HIBERNATE_URL)
                    .setProperty("hibernate.connection.username", LOGIN)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.default_schema", "mydb_for_hib")
                    .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                    .buildSessionFactory();
            } catch (HibernateException e) {
                throw new ExceptionInInitializerError("Initial of SessionFactory is failed");
            }
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
