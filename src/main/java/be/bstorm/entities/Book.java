package be.bstorm.entities;

import java.time.LocalDate;

public class Book {
    private String isbn;
    private String title;
    private String description;
    private LocalDate publicationDate;
    private int authorId;
    private Author author;

    public Book(){}

    private Book(String isbn, String title, String description, LocalDate publicationDate) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
    }

    public Book(String isbn, String title, String description, LocalDate publicationDate, int authorId) {
        this(isbn, title, description, publicationDate);
        this.authorId = authorId;
    }

    public Book(String isbn, String title, String description, LocalDate publicationDate, Author author) {
        this(isbn, title, description, publicationDate, author.getId());
        this.author = author;
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

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publicationDate=" + publicationDate +
                ", authorId=" + authorId +
                ", author=" + author +
                '}';
    }
}
