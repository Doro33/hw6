package ir.maktab_hw6.menu.userOption;

import ir.maktab_hw6.entity.Article;
import ir.maktab_hw6.menu.SetDate;
import ir.maktab_hw6.menu.SignIn;
import ir.maktab_hw6.repository.ArticleRepository;
import ir.maktab_hw6.repository.UserRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;



public class AddArticle {
    static Scanner scanner = new Scanner(System.in);

    private static String setTitle() throws SQLException {
        String title;
        int counter = 0;
        do {
            if (0 < counter)
                System.out.println("This title has already been made.");
            System.out.println("0) Back.");
            System.out.print("Title: ");
            title = scanner.nextLine();
            counter++;
            //should change
            if (title.matches("0"))
                UserOptionMenu.startUserMenu();
        } while ((ArticleRepository.titleContains(title)));
        return title;
    }

    private static LocalDate createDate() {
        System.out.println("Create date:");
        return SetDate.setDate();
    }

    protected static boolean isPublished() {
        boolean isPublished = false;
        System.out.println("Do you want to publish this article?");
        System.out.print("y as yes / n as no "); // 0 as back: ");
        String input = scanner.next().toLowerCase();
        while (!input.isEmpty()) {
            // if (input.matches("0"))
            //will be changed
            //MenuStarter.startMenu();
            if (input.matches("y")) {
                isPublished = true;
                break;
            } else if (input.matches("n")) {
                break;
            } else {
                System.out.println("Your input is not valid.");
                System.out.print("y as yes / n as no :");
                input = scanner.next().toLowerCase();
            }
        }
        return isPublished;
    }

    public static Article makeNewArticle() throws SQLException {
        String title = setTitle();
        System.out.println("0) Back.");
        System.out.print("Brief: ");
        String brief = scanner.next();
        if (brief.matches("0"))
            UserOptionMenu.startUserMenu();
        System.out.println("0) Back.");
        System.out.print("Content: ");
        String content = scanner.next();
        if (content.matches("0"))
            UserOptionMenu.startUserMenu();
        LocalDate createDate = createDate();
        boolean isPublished = isPublished();
        String username = SignIn.getUsername();
        int userId = UserRepository.findIdByUsername(username);
        return new Article(title, brief, content, createDate, isPublished, userId);
    }

    public static void addNewArticle() throws SQLException {
        Article article = makeNewArticle();
        if (article.getUserId() != 0)
            ArticleRepository.insert(article);
        System.out.println("Done");
        UserOptionMenu.startUserMenu();
    }
}