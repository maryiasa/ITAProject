package com.itacademy.mySQL;


import com.itacademy.dao.CarDAO;
import com.itacademy.model.Car;
import lombok.extern.log4j.Log4j2;
import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

@Log4j2
public class dbTest {

    private static final Logger log = LogManager.getLogger(dbTest.class);

    CarDAO carDAO = new CarDAO();

    @Test
    public void getCarModelByIdDB() {
        Car car = carDAO.getCarById(2);
        log.info(car.getCarModel());
    }

    @Test
    public void insertCarModelDB() {
        Faker faker = new Faker();
        String model = faker.vehicle().model();
        Car car = carDAO.insertCarModel(model);
    }

    @Test
    public void deleteCarByIdDB() {
        log.info(carDAO.getCarById(5).getCarModel());
        Car deleteCar = carDAO.deleteCarById(5);
        log.info(carDAO.getCarById(5).getCarModel());
    }

    @Test
    public void updateCarByIdDB() {
        Faker faker = new Faker();
        String model = faker.vehicle().model();
        log.info(carDAO.getCarById(1).getCarModel());
        Car updateCar = carDAO.updateCarById(model, 1);
        log.info(carDAO.getCarById(1).getCarModel());
    }
}
