package com.mirzoevnik.univer.java.task2.service;

import com.mirzoevnik.univer.java.task2.domain.Car;
import com.mirzoevnik.univer.java.task2.domain.Garage;
import com.mirzoevnik.univer.java.task2.exception.GarageException;
import lombok.SneakyThrows;

/**
 * @author mirzoevnik
 */
public class GarageServiceImpl implements GarageService {

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
    public void removeCar(Garage garage, Long carId) {
        Car car = garage.getCars()
                .stream()
                .filter(item -> item.getId().equals(carId))
                .findFirst()
                .orElseThrow(() -> new GarageException(String.format("Car with id=%s not found", carId)));
        garage.getCars().remove(car);
    }
}
