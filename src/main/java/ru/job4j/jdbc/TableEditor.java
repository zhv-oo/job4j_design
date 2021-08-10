package ru.job4j.jdbc;


import ru.job4j.io.Config;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Config config = new Config("app.properties");
        config.load();
        String url = config.value("url");
        String login = config.value("username");
        String password = config.value("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws SQLException {
            String sql = "create table if not exists " + tableName + ";";
            this.execQuery(sql);
    }

    public void dropTable(String tableName) throws SQLException {
            String sql = "delete table if not exists " + tableName + ";";
            this.execQuery(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
            String sql = "ALTER TABLE " + tableName + "ADD " + columnName + type + ";";
            this.execQuery(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
            String sql = "ALTER TABLE " + tableName + "DROP " + columnName + ";";
            this.execQuery(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
            String sql = "ALTER TABLE " + tableName + "RENAME COLUMN " + columnName + "TO " + newColumnName + ";";
            this.execQuery(sql);
    }

    private void execQuery(String query) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}