package com.Lab4;


import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        var connection = Connect.connect();

        var productRepository = new ProductRepository(connection);
        var productTypeRepository = new ProductTypeRepository(connection);
    }
}
