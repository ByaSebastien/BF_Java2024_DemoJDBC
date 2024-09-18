package be.bstorm.repositories.impls;

import be.bstorm.repositories.BaseRepository;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepositoryImpl<TId, TEntity> implements BaseRepository<TId, TEntity> {

    private static final String URL = "jdbc:postgresql://localhost:5432/demo_jdbc";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private final String tableName;
    private final String columnIdName;
    private final boolean isGenerated;

    public BaseRepositoryImpl(String tableName, String columnIdName, boolean isGenerated) {
        this.tableName = tableName;
        this.columnIdName = columnIdName;
        this.isGenerated = isGenerated;
    }


    @Override
    public List<TEntity> findAll() {

        try (Connection conn = getConnection()) {

            String query = "select * from " + tableName;
            PreparedStatement psmt = conn.prepareStatement(query);

            ResultSet rs = psmt.executeQuery();
            System.out.println(query);

            List<TEntity> entities = new ArrayList<>();

            while (rs.next()) {
                entities.add(buildEntity(rs));
            }

            return entities;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TEntity findById(TId id) {

        try (Connection conn = getConnection()) {
            String query = "select * from " + tableName + " where " + columnIdName + " = ?";
            PreparedStatement psmt = conn.prepareStatement(query);

            psmt.setObject(1, id);

            ResultSet rs = psmt.executeQuery();

            System.out.println(query);

            if (!rs.next()) {
                throw new RuntimeException("Could not find entity with id " + id);
            }

            return buildEntity(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TId save(TEntity entity) {

        try (Connection conn = getConnection()) {

            Field[] fields = entity.getClass().getDeclaredFields();
            StringBuilder columns = new StringBuilder();
            StringBuilder placeHolders = new StringBuilder();

            for (Field field : fields) {
                field.setAccessible(true);
                if (!(isGenerated && field.getName().equalsIgnoreCase(columnIdName))) {
                    columns.append(field.getName()).append(",");
                    placeHolders.append("?,");
                }
            }

            columns.deleteCharAt(columns.length() - 1);
            placeHolders.deleteCharAt(placeHolders.length() - 1);

            String query = "insert into " + tableName + " (" + columns + ") values (" + placeHolders + ")";

            PreparedStatement psmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            setPreparedStatementParameters(psmt, entity, fields);

            System.out.println(query);

            psmt.executeUpdate();

            ResultSet rs = psmt.getGeneratedKeys();

            if (!rs.next()) {
                throw new RuntimeException("Could not find generated key");
            }

            return (TId) rs.getObject(1);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(TId id, TEntity entity) {

        try (Connection conn = getConnection()) {

            Field[] fields = entity.getClass().getDeclaredFields();
            StringBuilder updateClause = new StringBuilder();

            for (Field field : fields) {
                field.setAccessible(true);
                if (!(isGenerated && field.getName().equalsIgnoreCase(columnIdName))) {
                    updateClause.append(field.getName()).append(" = ?,");
                }
            }

            updateClause.deleteCharAt(updateClause.length() - 1);

            String query = "update " + tableName + " set " + updateClause + " where " + columnIdName + " = ?";

            PreparedStatement psmt = conn.prepareStatement(query);

            setPreparedStatementParameters(psmt, entity, fields);
            psmt.setObject( isGenerated ? fields.length : fields.length + 1, id);

            System.out.println(query);

            return psmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(TId id) {

        try (Connection conn = getConnection()) {

            String query = "delete from " + tableName + " where " + columnIdName + " = ?";
            PreparedStatement psmt = conn.prepareStatement(query);
            psmt.setObject(1, id);

            System.out.println(query);

            return psmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setPreparedStatementParameters(PreparedStatement psmt, TEntity entity, Field[] fields) throws IllegalAccessException, SQLException {
        int index = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(entity);
            if (!(isGenerated && field.getName().equalsIgnoreCase(columnIdName))) {
                psmt.setObject(index++, value);
            }
        }
    }

    protected abstract TEntity buildEntity(ResultSet rs) throws SQLException;

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
