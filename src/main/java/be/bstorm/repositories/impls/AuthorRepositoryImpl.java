package be.bstorm.repositories.impls;

import be.bstorm.entities.Author;
import be.bstorm.repositories.AuthorRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRepositoryImpl extends BaseRepositoryImpl<Integer, Author> implements AuthorRepository {

    public AuthorRepositoryImpl() {
        super("author", "id",true);
    }

    @Override
    protected Author buildEntity(ResultSet rs) throws SQLException {
        return new Author(
                rs.getInt("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getDate("birthdate").toLocalDate()
        );
    }
}
