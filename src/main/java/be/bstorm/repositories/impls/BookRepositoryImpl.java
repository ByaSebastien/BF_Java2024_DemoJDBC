package be.bstorm.repositories.impls;

import be.bstorm.entities.Book;
import be.bstorm.repositories.BookRepository;

import java.sql.*;

public class BookRepositoryImpl extends BaseRepositoryImpl<String, Book> implements BookRepository {

    public BookRepositoryImpl() {
        super("book","isbn",false);
    }

    @Override
    protected Book buildEntity(ResultSet rs) throws SQLException {
        return new Book(
                rs.getString("isbn"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("release_date").toLocalDate(),
                rs.getInt("author_id")
        );
    }
}
