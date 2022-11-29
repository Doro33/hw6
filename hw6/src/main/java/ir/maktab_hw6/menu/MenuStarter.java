package ir.maktab_hw6.menu;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuStarter {
    static Scanner scanner = new Scanner(System.in);
    private static String input;

    private static void showMenu() {
        System.out.println("""
                ----------------
                Start Menu:
                1) Sign In
                2) Sign Up
                3) Show Published Article
                4) Exit.""");
    }

    private static void getOption() throws SQLException {
        System.out.println("----------------");
        System.out.print("What Do You Want To Do? ");
        input = scanner.nextLine();
        while (!input.isEmpty()) {
            switch (input) {
                case "1" -> {
                    System.out.println("----------------");
                    SignIn.signIn();
                }
                case "2" -> {
                    System.out.println("----------------");
                    SignUp.signUp();
                }
                case "3" -> {
                    System.out.println("----------------");
                    ShowPublishedArticle.publishedArticleOperator();
                }
                case "4" -> {
                    System.out.println("----------------");
                    exitCheck();
                }
                default -> {
                    System.out.println("Your input is not valid.");
                    System.out.print("you can choose 1/2/3/4 : ");
                    input = scanner.next();
                }
            }
        }
    }

    private static void exitCheck() throws SQLException {
        System.out.println("Are You Sure You Want To Exit?");
        System.out.print("y as yes / n as no: ");
        input = scanner.next().toLowerCase();
        while (!input.isEmpty()) {
            if (input.matches("y")) {
                System.out.println("Good Bye.");
                System.out.println("----------------");
                System.exit(0);
            } else if (input.matches("n")) {
                startMenu();
            } else {
                System.out.println("Your Input Is Not Valid.");
                System.out.print("y as yes / n as no : ");
                input = scanner.next().toLowerCase();
            }
        }
    }

    public static void startMenu() throws SQLException {
        showMenu();
        getOption();
    }
}