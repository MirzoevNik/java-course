package com.mirzoevnik.univer.java.task2.service;

import com.mirzoevnik.univer.java.task2.domain.Car;
import com.mirzoevnik.univer.java.task2.domain.Garage;
import com.mirzoevnik.univer.java.task2.exception.GarageException;

/**
 * @author mirzoevnik
 */
public interface GarageService {

    /**
     * Загрузить гараж из внешнего источника
     * @return гараж
     */
    Garage loadGarage();

    /**
     * Сохранить гараж
     * @param garage гараж
     */
    void save(Garage garage);

    /**
     * Добавить машину в гараж
     * @param garage гараж
     * @param car машина
     */
    void addCar(Garage garage, Car car) throws GarageException;

    /**
     * Убрать машину из гаража
     * @param garage гараж
     * @param carNumber номер машины
     */
    void removeCar(Garage garage, String carNumber) throws GarageException;
}
