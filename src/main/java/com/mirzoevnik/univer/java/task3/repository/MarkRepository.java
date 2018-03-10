package com.mirzoevnik.univer.java.task3.repository;

import com.mirzoevnik.univer.java.task3.domain.Mark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mirzoevnik
 */
@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {
}
