package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "create table users(Id INT PRIMARY KEY AUTO_INCREMENT, age INT, name VARCHAR(30), lastName VARCHAR(30));";
        try {
            Util.runUp(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "drop table users;";
        try {
            Util.runUp(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String username, String lastName, byte age) {
        String sql = String.format("insert users (name, lastName, age) values ('%s', '%s', %d);", username, lastName, age);
        try {
            Util.runUp(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = String.format("delete from users where id = %d;", id);
        try {
            Util.runUp(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String sql = "select * from users;";
        List<User> userList = new ArrayList<>();
        try {
            ResultSet resultSet = Util.runQuery(sql);
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
            Util.runUp(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
