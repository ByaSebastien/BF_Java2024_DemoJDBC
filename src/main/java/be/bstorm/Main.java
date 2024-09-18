package be.bstorm;

import be.bstorm.entities.Book;
import be.bstorm.repositories.BookRepository;
import be.bstorm.repositories.impls.BookRepositoryImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepositoryImpl();

        System.out.println(bookRepository.findById("9780451524935"));

//        bookRepository.findAll().forEach(System.out::println);
    }
}