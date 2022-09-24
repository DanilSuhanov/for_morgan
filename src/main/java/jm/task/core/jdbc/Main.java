package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Util.init();
            UserService userService = new UserServiceImpl();
            userService.createUsersTable();
            userService.saveUser("Name1", "LastName1", (byte) 20);
            userService.saveUser("Name2", "LastName2", (byte) 21);
            userService.saveUser("Name3", "LastName3", (byte) 22);
            userService.getAllUsers().iterator().forEachRemaining(System.out :: println);
            userService.cleanUsersTable();
            userService.dropUsersTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                Util.closeConnection();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
