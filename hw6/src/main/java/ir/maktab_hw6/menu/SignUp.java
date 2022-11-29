package ir.maktab_hw6.menu;

import ir.maktab_hw6.entity.User;
import ir.maktab_hw6.repository.UserRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class SignUp {
    static Scanner scanner = new Scanner(System.in);

    private static LocalDate setBirthday() {
        System.out.println("Birthday:");
        return SetDate.setDate();
    }

    private static String setUsername() throws SQLException {
        String username;
        int counter = 0;
        System.out.println("0) Back.");
        do {
            if (0 < counter)
                System.out.println("This username has already been taken.");

            System.out.print("Username: ");
            username = scanner.next();
            counter++;
            if (username.matches("0"))
                MenuStarter.startMenu();
        } while ((UserRepository.usernameContains(username)));
        return username;
    }

    public static String setNationalCode() throws SQLException {
        String nationalCode;
        int maximumRepetition = 0;
        System.out.println("----------------");
        System.out.println("0) Back.");
        do {
            if (0 < maximumRepetition)
                System.out.println("This national code has already made an account.");
            System.out.print("National code:");
            nationalCode = scanner.next();
            maximumRepetition++;
            if (nationalCode.matches("0"))
                MenuStarter.startMenu();
        } while ((UserRepository.nationalCodeContains(nationalCode)));
        return nationalCode;
    }

    public static void signUp() throws SQLException {
        System.out.println("Sign Up:");
        String username = setUsername();
        String nationalCode = setNationalCode();
        System.out.println("----------------");
        LocalDate birthday = setBirthday();
        User user = new User(username, nationalCode, birthday);
        UserRepository.insert(user);
        System.out.println("----------------");
        System.out.println("""
                Your Account Is Created Successfully.
                Your Password Is Your National Code.
                You Can Change Your Password After Sign In.""");
        SignIn.signIn();
    }
}