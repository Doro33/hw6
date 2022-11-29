package ir.maktab_hw6.menu;

import ir.maktab_hw6.menu.userOption.UserOptionMenu;
import ir.maktab_hw6.repository.UserRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class SignIn {
    static Scanner scanner = new Scanner(System.in);
    private static String username;

    public static void setUsername(String username) {
        SignIn.username = username;
    }

    public static String getUsername() {
        return username;
    }

    private static String checkUsername() throws SQLException {
        System.out.println("0) Back.");
        String username;
        int counter = 0;
        do {
            if (0 < counter)
                System.out.println("The Username Is INCORRECT.");
            System.out.print("Username: ");
            username = scanner.next();
            counter++;
            if (username.matches("0"))
                MenuStarter.startMenu();
        } while (!(UserRepository.usernameContains(username)));
        return username;
    }

    private static boolean checkPassword(String username) throws SQLException {
        System.out.print("Password: ");
        String password = scanner.next();
        if (password.matches("0"))
            MenuStarter.startMenu();
        return (password.matches(UserRepository.findPasswordByUsername(username)));
    }


    public static void signIn() throws SQLException {
        System.out.println("Sign In:");
        boolean checkPassword;
        String username = checkUsername();
        System.out.println("----------------");
        System.out.println("0) Back.");
        int counter = 0;
        do {
            if (0 < counter)
                System.out.println("The Password Is INCORRECT.");
            checkPassword = checkPassword(username);
            counter++;
        } while (!checkPassword);
        setUsername(username);
        System.out.println("Signed In");
        UserOptionMenu.startUserMenu();
    }
}