package sample.dao;

import sample.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public interface DAO<T> {
    Connection db = DatabaseConnection.getConnection();
    ArrayList<T> getAll() throws SQLException;
    boolean save(T t) throws SQLException;
    boolean update(T t);
    boolean delete(T t) throws SQLException;
}
