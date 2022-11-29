package ir.maktab_hw6.menu.userOption;

import ir.maktab_hw6.menu.MenuStarter;

import java.sql.SQLException;
import java.util.Scanner;

public class UserOptionMenu {
    static Scanner scanner = new Scanner(System.in);

    ///public static String username;
    private static void showUserOption() {
        System.out.print("""
                ----------------
                User Option:
                1) Show My Articles.
                2) Edit My Articles.
                3) Add A New Article.
                4) Change Password.
                5) Sign Out.
                What Do You Want To Do?\s""");
    }


    private static void getOption() throws SQLException {
        String input = scanner.next();
        loop1:
        while (!input.isEmpty()) {
            switch (input) {
                case "1":
                    System.out.println("----------------");
                    ShowMyArticles.showMyArticles();
                    break;
                case "2":
                    System.out.println("----------------");
                    ArticleEditing.myArticleOperator();
                    break loop1;
                case "3":
                    System.out.println("----------------");
                    AddArticle.addNewArticle();
                    break loop1;
                case "4":
                    System.out.println("----------------");
                    PasswordChanging.changePassword();
                case "5":
                    System.out.println("----------------");
                    exitCheck();
                    MenuStarter.startMenu();
                    break loop1;
                default:
                    System.out.println("Your input is not valid.");
                    System.out.print("you can choose 1/2/3/4/5 : ");
                    input = scanner.next();
            }
        }

    }

    private static void exitCheck() throws SQLException {
        System.out.println("Are You Sure You Want To Sign Out?");
        System.out.print("y as yes / n as no: ");
        String input = scanner.next().toLowerCase();
        while (!input.isEmpty()) {
            if (input.matches("y")) {
                MenuStarter.startMenu();
                //System.exit(0);
            } else if (input.matches("n")) {
                startUserMenu();
            } else {
                System.out.println("Your Input Is Not Valid.");
                System.out.print("y as yes / n as no : ");
                input = scanner.next().toLowerCase();
            }
        }
    }

    public static void startUserMenu() throws SQLException {
        showUserOption();
        getOption();
    }
}
