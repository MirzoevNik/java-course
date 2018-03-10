package com.mirzoevnik.univer.java.task3.repository;

import com.mirzoevnik.univer.java.task3.domain.Garage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mirzoevnik
 */
@Repository
public interface GarageRepository extends CrudRepository<Garage, Long> {
}
