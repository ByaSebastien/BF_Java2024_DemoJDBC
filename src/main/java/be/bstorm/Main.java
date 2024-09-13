package be.bstorm;

import be.bstorm.entities.Author;
import be.bstorm.entities.Book;
import be.bstorm.repositories.BookRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepository();

        Book b = new Book("123",
                "test",
                "test...",
                LocalDate.of(1991,3,27),
                1);

        bookRepository.save(b);
    }
}