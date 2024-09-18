package be.bstorm.repositories;

import java.util.List;

public interface BaseRepository<TId,TEntity> {
    List<TEntity> findAll();
    TEntity findById(TId id);
    TId save(TEntity entity);
    boolean update(TId id, TEntity entity);
    boolean delete(TId id);
}
