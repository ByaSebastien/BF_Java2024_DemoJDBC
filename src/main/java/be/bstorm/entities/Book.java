package be.bstorm.entities;

import java.time.LocalDate;

public class Book {
    private String isbn;
    private String title;
    private String description;
    private LocalDate release_date;
    private int author_id;

    public Book(){}

    public Book(String isbn, String title, String description, LocalDate release_date, int author_id) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.author_id = author_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publicationDate=" + release_date +
                ", authorId=" + author_id +
                '}';
    }
}
