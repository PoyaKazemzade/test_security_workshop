package se.yrgo.integrations;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private String classification;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getClassification() {
        return classification;
    }
}
