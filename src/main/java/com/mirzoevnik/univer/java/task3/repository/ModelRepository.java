package com.mirzoevnik.univer.java.task3.repository;

import com.mirzoevnik.univer.java.task3.domain.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mirzoevnik
 */
@Repository
public interface ModelRepository extends CrudRepository<Model, Long> {
}
