package com.mirzoevnik.univer.java.task2.service;

import com.mirzoevnik.univer.java.task2.domain.Car;
import com.mirzoevnik.univer.java.task2.domain.Garage;
import com.mirzoevnik.univer.java.task2.exception.GarageException;
import com.mirzoevnik.univer.java.task2.util.JSONUtils;
import lombok.SneakyThrows;

/**
 * @author mirzoevnik
 */
public class GarageServiceImpl implements GarageService {

    private static final String FILE_NAME = "cars.json";

    /**
     * {@inheritDoc}
     */
    @Override
    public Garage loadGarage() {
        return JSONUtils.read(FILE_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Garage garage) {
        JSONUtils.write(FILE_NAME, garage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SneakyThrows
    public void addCar(Garage garage, Car car) {
        if (garage.getCars()
                .stream()
                .anyMatch(item -> item.getNumber().equals(car.getNumber()))) {
            throw new GarageException("Incorrect car's number");
        }
        garage.getCars().add(car);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SneakyThrows
    public void removeCar(Garage garage, String carNumber) {
        Car car = garage.getCars()
                .stream()
                .filter(item -> item.getNumber().equals(carNumber))
                .findFirst()
                .orElseThrow(() -> new GarageException(String.format("Car with number=%s not found", carNumber)));
        garage.getCars().remove(car);
    }
}
