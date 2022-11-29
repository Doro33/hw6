package ir.maktab_hw6.menu;

import ir.maktab_hw6.entity.Article;
import ir.maktab_hw6.repository.ArticleRepository;

import java.sql.SQLException;
import java.util.Scanner;
public class ShowPublishedArticle {
    static Scanner scanner = new Scanner(System.in);

    private static Article[] publishedArticles() throws SQLException {
        return ArticleRepository.showPublishedArticle();
    }

    private static void showPublishedArticle() throws SQLException {
        System.out.println("Published Article:" + '\n' +
                "0) Back.");
        Article.showArticlesInMenu(publishedArticles());
    }

    private static String notValidInputReaction() {
        System.out.print("Your Input Is Not Valid, Try Again: ");
        return scanner.next();
    }

    private static void getOption() throws SQLException {
        Article[] publishedArticles = publishedArticles();
        System.out.print("Please Enter The Number Of Operation: ");
        String input = scanner.next();
        while (!input.isEmpty()) {
            if (input.matches("0")) {
                MenuStarter.startMenu();
                break;
            } else if (input.matches("^[1-9]?[0-9]{1}$|^100$")) {
                int i = Integer.parseInt(input);
                if (i < publishedArticles.length) {
                    if (publishedArticles[i - 1] != null) {
                        System.out.println("----------------");
                        // System.out.println("0) Back.");
                        System.out.println(publishedArticles[i - 1]);
                        System.out.print("Press 0 to see published articles again. ");
                        input = scanner.next();
                        while (!input.isEmpty()) {
                            if (input.matches("0")) {
                                System.out.println("----------------");
                                publishedArticleOperator();
                                break;
                            } else {
                                System.out.println("Your Input Is Not Valid.");
                                System.out.print("Press 0 to see published articles again. ");
                                input = scanner.next();
                            }
                        }
                    } else
                        input = notValidInputReaction();
                } else
                    input = notValidInputReaction();
            } else
                input = notValidInputReaction();
        }
    }

    public static void publishedArticleOperator() throws SQLException {
        showPublishedArticle();
        getOption();
    }
}