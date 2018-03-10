package com.mirzoevnik.univer.java.task3.repository;

import com.mirzoevnik.univer.java.task3.domain.Car;
import org.springframework.data.repository.CrudRepository;

/**
 * @author mirzoevnik
 */
public interface CarRepository extends CrudRepository<Car, Long> {
}
