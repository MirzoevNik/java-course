package com.mirzoevnik.univer.java.task3.repository;

import com.mirzoevnik.univer.java.task3.domain.Car;
import com.mirzoevnik.univer.java.task3.domain.Garage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mirzoevnik
 */
@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findByGarage(Garage garage);
}
