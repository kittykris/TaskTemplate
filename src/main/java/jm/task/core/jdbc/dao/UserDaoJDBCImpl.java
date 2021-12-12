package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {
        //must be empty
    }

    public void createUsersTable() {
        try (Connection connection = getJDBCConnection()) {
            String sql = "CREATE TABLE IF NOT EXISTS Users(id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                    "name VARCHAR(30) NOT NULL, lastName VARCHAR(30) NOT NULL, age INT(3) NOT NULL);";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("Can't create table.");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = getJDBCConnection()) {
            String sql = "DROP TABLE IF EXISTS Users;";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("Can't drop table.");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = getJDBCConnection()) {
            String sql = "INSERT INTO Users(name, lastName, age) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setInt(3, age);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Can't insert User into table.");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = getJDBCConnection()) {
            String sql = "DELETE FROM Users WHERE id = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("There is no such ID.");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = getJDBCConnection()) {
            String sql = "SELECT * FROM Users";
            try (Statement statement = connection.createStatement()) {
                ResultSet result = statement.executeQuery(sql);
                while (result.next()) {
                    User user = new User();
                    user.setId(result.getLong(1));
                    user.setName(result.getString(2));
                    user.setLastName(result.getString(3));
                    user.setAge(result.getByte(4));
                    list.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("There is no table.");
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = getJDBCConnection()) {
            String sql = "DELETE FROM Users";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("Can't clean table.");
        }
    }
}
