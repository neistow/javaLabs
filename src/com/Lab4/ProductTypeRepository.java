package com.Lab4;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductTypeRepository implements RepositoryBase<ProductType> {
    private final Connection connection;

    public ProductTypeRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ProductType> GetAll() throws SQLException {
        var stmt = connection.createStatement();
        String sql = "select * from productTypes";

        var list = new ArrayList<ProductType>();

        var resultSet = stmt.executeQuery(sql);
        while (resultSet.next()) {
            var product = new ProductType(resultSet.getInt("id"), resultSet.getString("name"));
            list.add(product);
        }

        return list;
    }

    @Override
    public Optional<ProductType> GetById(int id) throws SQLException {
        var stmt = connection.createStatement();
        String sql = "select * from productTypes where id = " + id;

        var resultSet = stmt.executeQuery(sql);
        if (!resultSet.next()) {
            return Optional.empty();
        }

        return Optional.of(new ProductType(resultSet.getInt("id"), resultSet.getString("name")));
    }

    @Override
    public int Insert(ProductType entity) throws SQLException {
        var stmt = connection.createStatement();
        String sql = "insert into productTypes (name) values (" + "'" + entity.getName() + "'" + ")";
        stmt.execute(sql);

        var stmt2 = connection.createStatement();
        String sql2 = "select last_insert_rowid() as id";
        var resultSet = stmt2.executeQuery(sql2);
        return resultSet.getInt("id");
    }

    @Override
    public void Delete(int id) throws SQLException {
        var stmt = connection.createStatement();
        String sql = "delete from productTypes where id = " + id;
        stmt.execute(sql);
    }
}
