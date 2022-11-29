package ir.maktab_hw6.menu.userOption;

import ir.maktab_hw6.menu.SignIn;
import ir.maktab_hw6.repository.UserRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class PasswordChanging {
    static Scanner scanner= new Scanner(System.in);

    private static void checkOldPassword() throws SQLException {
        String username = SignIn.getUsername();
        System.out.println("0) Back.");
        System.out.print("Old password: ");
        String oldPassword = scanner.nextLine();
        if (oldPassword.matches("0"))
            UserOptionMenu.startUserMenu();
        if (!oldPassword.matches(UserRepository.findPasswordByUsername(username))) {
            System.out.println("Incorrect!");
            checkOldPassword();
        }
    }
    private static String checkNewPassword() throws SQLException {
        String newPassword ;
        int counter=0;
        while (true) {
            if(counter!=0)
                System.out.println("These Passwords Are Not Match.");
            System.out.println("0) Back.");
            System.out.print("new password: ");
             newPassword = scanner.nextLine();
            if (newPassword.matches("0"))
                UserOptionMenu.startUserMenu();
            System.out.print("Reenter Password for verification: ");
            String password1 = scanner.nextLine();
            if (password1.matches("0"))
                UserOptionMenu.startUserMenu();
            if (newPassword.matches(password1))
                break;
            counter++;
        }
        return newPassword;
    }

    public static void changePassword() throws SQLException {
        int id;
        String newPassword;
        String username=SignIn.getUsername();
        checkOldPassword();
        newPassword=checkNewPassword();
        id = UserRepository.findIdByUsername(username);
        UserRepository.changePassword(id, newPassword);
        System.out.println("Done");
        UserOptionMenu.startUserMenu();
    }
}