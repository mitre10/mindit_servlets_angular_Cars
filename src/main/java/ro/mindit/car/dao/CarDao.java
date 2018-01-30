package ro.mindit.car.dao;

import ro.mindit.car.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ro.mindit.car.util.Constants.*;

public class CarDao {
    private int idSeq = 1;
    private List<Car> cars;

    private Connection jdbcConnection;

    public CarDao() {
        final Car car = new Car() {{
            setId(idSeq++);
            setName("Car 1");
            setType("Sedan");
            setFuel("Diesel");
            setPrice(28000);
        }};
        cars = new ArrayList<Car>() {{
            add(car);
        }};
    }

    public List<Car> queryAll() {
        return cars;
    }

    public Car getCar(int id) {
        Car result = null;
        for (Car c : cars) {
            if (c.getId() == id) {
                result = c;
                break;
            }
        }
        return result;
    }


    public void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName(jdbcDriver);
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    public void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public Car findOne(int id) throws SQLException {
        Car car = null;
        String sql = "SELECT * FROM cars WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String type = resultSet.getString("type");
            String fuel = resultSet.getString("fuel");
            int price = resultSet.getInt("price");

            car = new Car(id, name, type, fuel, price);
        }

        resultSet.close();
        statement.close();

        return car;
    }

    public List<Car> findAll() throws SQLException {
        List<Car> todoList = new ArrayList<Car>();

        String sql = "SELECT * FROM cars";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String type = resultSet.getString("type");
            String fuel = resultSet.getString("fuel");
            int price = resultSet.getInt("price");
            Car car = new Car(id, name, type, fuel, price);
            todoList.add(car);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return todoList;
    }

}
