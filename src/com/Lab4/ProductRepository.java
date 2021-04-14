package com.Lab4;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProductRepository implements RepositoryBase<Product> {
    private final Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> GetAll() throws SQLException {
        var stmt = connection.createStatement();
        String sql = "select * from products";

        var list = new ArrayList<Product>();

        var resultSet = stmt.executeQuery(sql);
        while (resultSet.next()) {
            var product = new Product(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getFloat("price"),
                    new ProductType(resultSet.getInt("typeId"), resultSet.getString("typeName")));
            list.add(product);
        }

        return list;
    }

    @Override
    public Optional<Product> GetById(int id) throws SQLException {
        var stmt = connection.createStatement();
        String sql = "select p.id, p.name, p.price, pt.id as typeId, pt.name as typeName from main.products p join productTypes pt on pt.id = p.typeId where p.id = " + id;

        var resultSet = stmt.executeQuery(sql);
        if (!resultSet.next()) {
            return Optional.empty();
        }

        return Optional.of(new Product(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getFloat("price"), new ProductType(resultSet.getInt("typeId"), resultSet.getString("typeName"))));
    }

    @Override
    public int Insert(Product entity) throws SQLException {
        var stmt = connection.createStatement();
        String sql = "insert into main.products (name, price, typeId) values (" + "'" + entity.getName() + "'" + ", " + entity.getPrice() + ", " + entity.getProductType().getId() + ")";
        stmt.execute(sql);

        var stmt2 = connection.createStatement();
        String sql2 = "select last_insert_rowid() as id";
        var resultSet = stmt2.executeQuery(sql2);
        return resultSet.getInt("id");
    }

    @Override
    public void Delete(int id) throws SQLException {
        var stmt = connection.createStatement();
        String sql = "delete from main.products where id = " + id;
        stmt.execute(sql);
    }
}
