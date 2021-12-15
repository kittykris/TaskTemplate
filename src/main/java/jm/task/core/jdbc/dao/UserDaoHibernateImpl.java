package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    //Методы создания и удаления таблицы пользователей в классе UserHibernateDaoImpl должны быть реализованы с помощью SQL.
    public UserDaoHibernateImpl() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS users(id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "name VARCHAR(30) NOT NULL, lastName VARCHAR(30) NOT NULL, age INT(3) NOT NULL);";

        NativeQuery sql1 = session.createSQLQuery(sql);
        sql1.addEntity(User.class);
        //TODO
    }


    @Override
    public void createUsersTable() {
        //TODO
    }

    @Override
    public void dropUsersTable() {
        //TODO
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        //TODO
        return null;
    }

    @Override
    public void cleanUsersTable() {
        //TODO
    }
}
