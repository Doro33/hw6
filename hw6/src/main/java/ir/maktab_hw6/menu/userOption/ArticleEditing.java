package ir.maktab_hw6.menu.userOption;

import ir.maktab_hw6.entity.Article;
import ir.maktab_hw6.menu.SetDate;
import ir.maktab_hw6.menu.SignIn;
import ir.maktab_hw6.repository.ArticleRepository;
import ir.maktab_hw6.repository.UserRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class ArticleEditing {
    final static Article EXCHANGE_ARTICLE=new Article("0","0","0", LocalDate.of(0,1,1),false,0);
    static Scanner scanner = new Scanner(System.in);
    private static Article[] myArticles() throws SQLException {
        String username = SignIn.getUsername();
        int id = UserRepository.findIdByUsername(username);
        return ArticleRepository.showUserArticle(id);
    }
    private static String notValidInputReaction() {
        System.out.print("Your Input Is Not valid, Try Again: ");
        return scanner.next();
    }
    public static void showMyArticles() throws SQLException {
        Article[] myArticles = myArticles();
        System.out.println("Edit My Article.");
        System.out.println("0) Back.");
        Article.showMyArticles(myArticles);
    }

    private static void editOption(int i) throws SQLException {
        System.out.println("0) Back.");
        System.out.println("1) Published/Unpublished.");
        System.out.println("2) Edit Article.");
        System.out.print("What Do You Want To Do? ");
        String input = scanner.next();
        Article[] myArticles = myArticles();
        loop1:
        while (!input.isEmpty()) {
            switch (input) {
                case "0":
                    UserOptionMenu.startUserMenu();
                    break;
                case "1":
                    int articleId=myArticles[i-1].getArticleId();
                    ArticleRepository.changePublishStatus(articleId);
                    System.out.println("Done.");
                    //System.out.println(ArticleRepository.getArticle(i-1));
                    UserOptionMenu.startUserMenu();
                    break loop1;
                case "2":
                    //will be changed
                    articleId=myArticles[i-1].getArticleId();
                    editAnArticle(articleId);
                    break loop1;
                default:
                    System.out.println("Your Input Is Not Valid.");
                    System.out.print("you can choose 0/1/2 : ");
                    input = scanner.next();
            }
        }
    }
    public static void getOption() throws SQLException {
        System.out.print("Which Article Do You Want To Edit: ");
        String input = scanner.next();
        Article[] myArticles = myArticles();
        while (!input.isEmpty()) {
            if (input.matches("0")) {
                //will be changed
                UserOptionMenu.startUserMenu();
                break;
            } else if (input.matches("^[1-9]?[0-9]{1}$|^100$")) {
                int i = Integer.parseInt(input);
                if (i < myArticles.length) {
                    if (myArticles[i - 1] != null) {
                        System.out.println("----------------");
                        System.out.println(myArticles[i - 1]);
                        editOption(i);
                        //System.out.print("Press 0 to see published articles again. ");
                        input = scanner.next();
                        while (!input.isEmpty()) {
                            if (input.matches("0")) {
                                //should change later
                                myArticleOperator();
                                break;
                            } else {
                                System.out.println("Your input is not valid.");
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


   //can not use setTitle from AddArticle because of database
    private static String setTitle() throws SQLException {
        String title;
        int counter = 0;
        do {
            if (0 < counter)
                System.out.println("This Title Has Already Been Made.");
            System.out.print("Title: ");
            title = scanner.next();
            counter++;
        } while ((ArticleRepository.titleContains(title)));
        return title;
    }

    //can not use setTitle from AddArticle because of database
    public static Article makeNewArticle() throws SQLException {
        String title = setTitle();
        System.out.print("Brief: ");
        String brief = scanner.next();
        System.out.print("Content: ");
        String content = scanner.next();
        LocalDate createDate = SetDate.setDate();
        boolean isPublished = AddArticle.isPublished();
        String username = SignIn.getUsername();
        int userId = UserRepository.findIdByUsername(username);
        return new Article(title, brief, content, createDate, isPublished, userId);
    }

    public static void editAnArticle(int articleId) throws SQLException {
        System.out.println("You Cannot Get Back During This Process."+'\n'+
                            "Dont Worry, If You Suddenly Make A Mistake, You Can Edit Your Article Again.");
        //next line was wrote because maybe user doesn't want to change article's name
        ArticleRepository.update(articleId,EXCHANGE_ARTICLE);
        //user can't take other articles' name.
        Article editedArticle= makeNewArticle();
        ArticleRepository.update(articleId,editedArticle);
        System.out.println("Done.");
        UserOptionMenu.startUserMenu();
    }

    public static void myArticleOperator() throws SQLException {
        showMyArticles();
        getOption();
    }
}