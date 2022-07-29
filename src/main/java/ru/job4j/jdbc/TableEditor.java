package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        try {
            initConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        ClassLoader loader = StatementDemo.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("jdbc.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) {
        query(String.format(
                "CREATE TABLE IF NOT EXISTS %s(%s, %s);",
                tableName,
                "id SERIAL PRIMARY KEY",
                "name varchar(255)"
        ));
    }

    public void dropTable(String tableName) {
        query(String.format(
                "DROP TABLE IF EXISTS %s;",
                tableName
        ));

    }

    public void addColumn(String tableName, String columnName, String type) {
        query(String.format(
                "ALTER TABLE %s ADD COLUMN %s %s;",
                tableName,
                columnName,
                type
        ));
    }

    public void dropColumn(String tableName, String columnName) {
        query(String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName,
                columnName
        ));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        query(String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName
        ));
    }

    public void query(String str) {
        try (var statement = connection.createStatement()) {
            statement.execute(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor te = new TableEditor(new Properties());
        /*Создание таблицы*/
        te.createTable("new_demo_table");
        System.out.println(StatementDemo.getTableScheme(te.connection, "new_demo_table"));
        /*Добавление столбца*/
        te.addColumn("new_demo_table", "description", "text");
        System.out.println(StatementDemo.getTableScheme(te.connection, "new_demo_table"));
        /*Переименование столбца*/
        te.renameColumn("new_demo_table", "description", "remarks");
        System.out.println(StatementDemo.getTableScheme(te.connection, "new_demo_table"));
        /*Удаление столбца*/
        te.dropColumn("new_demo_table", "remarks");
        System.out.println(StatementDemo.getTableScheme(te.connection, "new_demo_table"));
        /*Удаление таблицы*/
        te.dropTable("new_demo_table");
        System.out.println(StatementDemo.getTableScheme(te.connection, "new_demo_table"));
    }
}
