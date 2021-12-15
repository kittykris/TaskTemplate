package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_JDBC_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "12345";


    public static Connection getJDBCConnection() {
        Connection connection = null;
        try {
            Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection(MYSQL_JDBC_URL, LOGIN, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Problem with connection");
        }
        return connection;
    }

    // В класс Util должна быть добавлена конфигурация для Hibernate (рядом с JDBC), без использования xml.
    private static final String MYSQL_HIBERNATE_URL = "jdbc:mysql://localhost:3306/mydb_for_hib";
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", MYSQL_DRIVER)
                    .setProperty("hibernate.connection.url", MYSQL_HIBERNATE_URL)
                    .setProperty("hibernate.connection.username", LOGIN)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.default_schema", "mydb_for_hib")
                    .setProperty("hibernate.current_session_context_class","thread")
                    .addAnnotatedClass(jm.task.core.jdbc.model.User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            try {
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (HibernateException e) {
                throw new ExceptionInInitializerError("Initial SessionFactory is failed");
            }
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        getSessionFactory().close();
    }
}
