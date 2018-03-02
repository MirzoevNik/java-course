package com.mirzoevnik.univer.java.task2.service;

import com.mirzoevnik.univer.java.task2.domain.Car;
import com.mirzoevnik.univer.java.task2.domain.Garage;

/**
 * @author mirzoevnik
 */
public class GarageServiceImpl implements GarageService {

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCar(Garage garage, Car car) {
        garage.getCars().add(car);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCar(Garage garage, Car car) {
        garage.getCars().remove(car);
    }
}
