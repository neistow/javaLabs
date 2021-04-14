package com.Lab4;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryBase<R> {
    List<R> GetAll() throws SQLException;
    R GetById(int id) throws SQLException;
    int Insert(R entity) throws SQLException;
    void Delete(int id) throws SQLException;
}
