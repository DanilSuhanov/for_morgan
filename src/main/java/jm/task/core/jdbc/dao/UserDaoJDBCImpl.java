package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    private Statement statement;

    {
        try {
            statement = Util.getCon().createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createUsersTable() {
        String sql = "create TABLE IF NOT EXISTS users(Id INT PRIMARY KEY AUTO_INCREMENT, age INT, name VARCHAR(30), lastName VARCHAR(30));";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "drop table if exists users;";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String username, String lastName, byte age) {
        String sql = String.format("insert users (name, lastName, age) values ('%s', '%s', %d);", username, lastName, age);
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from users where id = ?;";
        try {
            PreparedStatement prepSt = Util.getCon().prepareStatement(sql);
            prepSt.setLong(1, id);
            prepSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String sql = "select * from users;";
        List<User> userList = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                userList.add(new User(resultSet.getString(3), resultSet.getString(4), resultSet.getByte(2)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "delete from users;";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
