package com.mirzoevnik.univer.java.task3.mapper;

import com.mirzoevnik.univer.java.task3.domain.Car;
import com.mirzoevnik.univer.java.task3.service.GarageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

/**
 * @author mirzoevnik
 */
@Repository
public class CarMapper implements RowMapper<Car> {

    private GarageService garageService;

    @Autowired
    public CarMapper(GarageService garageService) {
        this.garageService = garageService;
    }

    @Override
    @SneakyThrows
    public Car mapRow(ResultSet rs, int rowNum) {
        Car car = new Car();
        car.setId(rs.getLong("id"));
        car.setNumber(rs.getString("number"));
        car.setGarage(garageService.getGarageById(rs.getLong("garage_id")));
        car.setModel(garageService.getModelById(rs.getLong("model_id")));
        return car;
    }
}
