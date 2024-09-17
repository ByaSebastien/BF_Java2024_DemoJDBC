package be.bstorm.repositories;

import be.bstorm.entities.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();
    Book findById(String isbn);
    String save(Book book);
    boolean update(String isbn, Book book);
    boolean delete(String isbn);
}
