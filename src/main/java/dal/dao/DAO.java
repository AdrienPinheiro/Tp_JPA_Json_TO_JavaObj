package dal.dao;

import dal.DALException;

import java.util.List;

/**
 * @param <T>
 * All same call BDD method
 */
public interface DAO<T> {
    public void insert(T data) throws DALException;
    public void delete(T data) throws DALException;
    public void update(T data) throws DALException;
    public T selectById(long id) throws DALException;
    public List<T> selectAll() throws DALException;
}
