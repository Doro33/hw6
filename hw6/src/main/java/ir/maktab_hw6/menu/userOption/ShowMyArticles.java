package ir.maktab_hw6.menu.userOption;

import ir.maktab_hw6.entity.Article;
import ir.maktab_hw6.menu.SignIn;
import ir.maktab_hw6.repository.ArticleRepository;
import ir.maktab_hw6.repository.UserRepository;

import java.sql.SQLException;
import java.util.Scanner;


public class ShowMyArticles {
    static Scanner scanner = new Scanner(System.in);

    private static Article[] myArticles() throws SQLException {
        String username = SignIn.getUsername();
        int id = UserRepository.findIdByUsername(username);
        return ArticleRepository.showUserArticle(id);
    }

    protected static void goBack() throws SQLException {
        System.out.print("press 0 to get back. ");
        String input = scanner.next();
        while (!input.isEmpty()) {
            if (input.matches("0")) {
                UserOptionMenu.startUserMenu();
                break;
            } else {
                System.out.println("Your input is not valid.");
                System.out.print("Press 0 to get back. ");
                input = scanner.next();
            }
        }
    }

    public static void showMyArticles() throws SQLException {
        Article[] myArticles= myArticles();
        Article.showMyArticles(myArticles);
        goBack();
    }
}