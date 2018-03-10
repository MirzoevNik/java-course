package com.mirzoevnik.univer.java.task3.service;

import com.mirzoevnik.univer.java.task3.domain.Car;
import com.mirzoevnik.univer.java.task3.domain.Garage;
import com.mirzoevnik.univer.java.task2.exception.GarageException;
import com.mirzoevnik.univer.java.task3.domain.Mark;
import com.mirzoevnik.univer.java.task3.domain.Model;

/**
 * @author mirzoevnik
 */
public interface GarageService {

    /**
     * Получить гараж по id
     * @param id идентификатор гаража
     * @return гараж
     */
    Garage getGarageById(Long id);

    /**
     * Получить машину по id
     * @param id идентификатор машины
     * @return машина
     */
    Car getCarById(Long id);

    /**
     * Получить модель по id
     * @param id идентификатор модели
     * @return модель
     */
    Model getModelById(Long id);

    /**
     * Получить марку по id
     * @param id идентификатор марки
     * @return марка
     */
    Mark getMarkById(Long id);

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
     * Сохранить марку
     * @param mark марка
     */
    void save(Mark mark);

    /**
     * Сохранить модель
     * @param model модель
     */
    void save(Model model);

    /**
     * Сохранить машину
     * @param car машина
     */
    void save(Car car);

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

    /**
     * Удалить машину
     * @param car машина
     */
    void remove(Car car);
}
