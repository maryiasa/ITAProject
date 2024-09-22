package com.itacademy.dao;

import com.itacademy.BasicConnectionPool;
import com.itacademy.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDAO {

    BasicConnectionPool connectionPool = BasicConnectionPool.create();

    public Car getCarById(int id) {
        Car car = new Car();
        Connection connection= null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mydb.Cars where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                car.setCarModel(resultSet.getString("model"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return car;
    }

    public Car insertCarModel(String model) {
        Car car = new Car();
        Connection connection= null;
        try {
            connection= connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mydb.Cars (model) VALUES (?)");
            preparedStatement.setString(1, model);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return car;
    }

    public Car deleteCarById(int id) {
        Car car = new Car();
        Connection connection= null;
        try {
            connection= connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM mydb.Cars WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return car;
    }

    public Car updateCarById(String model, int id) {
        Car car = new Car();
        Connection connection= null;
        try {
            connection= connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE mydb.Cars SET model = ? WHERE id = ?");
            preparedStatement.setString(1, model);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return car;
    }

}
