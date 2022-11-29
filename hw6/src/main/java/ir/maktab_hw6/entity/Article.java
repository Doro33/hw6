package ir.maktab_hw6.entity;

import java.time.LocalDate;

public class Article {
    private int articleId;
    private String title;
    private String brief;
    private String content;
    private LocalDate createDate;
    private boolean isPublished;
    private int userId;

    public Article(String title, String brief, String content, LocalDate createDate, boolean isPublished, int userId) {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.createDate = createDate;
        this.isPublished = isPublished;
        this.userId = userId;
    }

    public Article() {
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private String publishToString() {
        if (isPublished)
            return "published";
        else
            return "unpublished";
    }

    public String toString() {
        return "Article id: " + articleId
                + " | title: " + title
                + " | brief: " + brief
                + " | content: " + content
                + " | create date: " + createDate
                + " | publish status: " + publishToString();
    }

    private String showArticlesInMenu() {
        return "Article id:" + articleId
                + " | title:" + title
                + " | brief:" + brief;
    }

    public static void showArticlesInMenu(Article[] articles) {
        if (articles[0] == null)
            System.out.println("There Is No Published Article To Show.");
        for (int i = 0; i < 100; i++) {
            if (articles[i] != null)
                System.out.println(i + 1 + ") " + articles[i].showArticlesInMenu());
        }
    }

    public static void showMyArticles(Article[] myArticles) {
        System.out.println("My Articles:");
        if (myArticles[0] == null)
            System.out.println("You Have Not Made An Article Yet.");
        for (int i = 0; i < 100; i++) {
            if (myArticles[i] != null)
                System.out.println(i + 1 + ") " + myArticles[i]);
        }
    }
}