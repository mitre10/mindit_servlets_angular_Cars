/**
 *
 */
package ro.mindit.car.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ro.mindit.car.dao.CarDao;
import ro.mindit.car.model.Car;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CarResource extends HttpServlet {

    private CarDao carDao;

    @Override
    public void init() throws ServletException {
        carDao = new CarDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = "";
        ObjectMapper obj = new ObjectMapper();
        ServletInputStream reqInputStream = req.getInputStream();
        Scanner sc = new Scanner(reqInputStream, "UTF-8").useDelimiter("\\A");
        json = sc.next();
        Car car = obj.readValue(json, Car.class);
        try {
            carDao.connect();
            carDao.addCar(car);
            carDao.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json="";
        ObjectMapper obj = new ObjectMapper();
        ServletInputStream reqInputStream = req.getInputStream();
        Scanner sc = new Scanner(reqInputStream, "UTF-8").useDelimiter("\\A");
        json = sc.next();
        Car car = obj.readValue(json, Car.class);
        try {
            carDao.connect();
            carDao.updateCarById(car.getName(), car.getType(), car.getFuel(), car.getPrice(), car.getId());
            carDao.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            carDao.connect();
            carDao.deleteCarByiD(Integer.parseInt(id));
            carDao.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // set response content type
        response.setContentType("application/json");

//        String json = getTodoFromMemory(request);
		String json = getCarFromDb(request);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    @Override
    public void destroy() {
    }

    private String getCarFromDb(HttpServletRequest request) throws JsonProcessingException {
        String json = "";
        ObjectMapper objectMapper = new ObjectMapper();

        String id = request.getParameter("id");
        try {
            // Connect to the database
            carDao.connect();

            if (id != null) {
                Car car = carDao.findOne(Integer.parseInt(id));
                json = objectMapper.writeValueAsString(car);
            } else {
                List<Car> cars = carDao.findAll();
                json = objectMapper.writeValueAsString(cars);
            }

            // Disconnect from the database
            carDao.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return json;
    }

}