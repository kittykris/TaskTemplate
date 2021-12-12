package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Maria", "Ivanova", (byte)23);
        userService.saveUser("Olga", "Koroleva", (byte)23);
        userService.saveUser("Ivan", "Kirillov", (byte)23);
        userService.saveUser("Kirill", "Sapozhnikov", (byte)23);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
