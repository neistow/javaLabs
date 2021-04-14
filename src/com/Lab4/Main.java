package com.Lab4;


import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        var connection = Connect.connect();

        var productRepository = new ProductRepository(connection);
        var productTypeRepository = new ProductTypeRepository(connection);

        var t1 = new ProductType(1, "Type 2");

        var p1 = new Product(0,"Product one",25.5f, t1);
        System.out.println(productRepository.Insert(p1));
    }
}
