package com.mirzoevnik.univer.java.task3.service;

import com.mirzoevnik.univer.java.task2.exception.GarageException;
import com.mirzoevnik.univer.java.task3.domain.Car;
import com.mirzoevnik.univer.java.task3.domain.Garage;
import com.mirzoevnik.univer.java.task3.domain.Mark;
import com.mirzoevnik.univer.java.task3.domain.Model;
import com.mirzoevnik.univer.java.task3.mapper.CarMapper;
import com.mirzoevnik.univer.java.task3.mapper.MarkMapper;
import com.mirzoevnik.univer.java.task3.mapper.ModelMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mirzoevnik
 */
@Transactional
@Service
public class GarageServiceJDBCImpl implements GarageService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GarageServiceJDBCImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Garage getGarageById(Long id) {
        final String QUERY = "SELECT * FROM garage WHERE id = ?";
        return jdbcTemplate.queryForObject(QUERY, new Object[] {id}, new BeanPropertyRowMapper<>(Garage.class));
    }

    @Override
    public Car getCarById(Long id) {
        final String QUERY = "SELECT * FROM car WHERE id = ?";
        return jdbcTemplate.queryForObject(QUERY, new Object[] {id}, new CarMapper(this));
    }

    @Override
    public Model getModelById(Long id) {
        final String QUERY = "SELECT * FROM model WHERE id = ?";
        return jdbcTemplate.queryForObject(QUERY, new Object[] {id}, new ModelMapper(this));
    }

    @Override
    public Mark getMarkById(Long id) {
        final String QUERY = "SELECT * FROM mark WHERE id = ?";
        return jdbcTemplate.queryForObject(QUERY, new Object[] {id}, new MarkMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Garage loadGarage() {
        List<Garage> garages = getAllGarages();
        if (garages.isEmpty()) {
            Garage garage = new Garage();
            garage.setName("default");
            return garage;
        }
        Garage garage = garages.get(0);
        garage.setCars(getCarsByGarage(garage));
        return garage;
    }

    private List<Garage> getAllGarages() {
        final String QUERY = "SELECT * FROM garage";
        return jdbcTemplate.query(QUERY, new BeanPropertyRowMapper<>(Garage.class));
    }

    private List<Car> getCarsByGarage(Garage garage) {
        final String QUERY = "SELECT * FROM car WHERE garage_id = ?";
        return jdbcTemplate.query(QUERY, new Object[] {garage.getId()}, new CarMapper(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Garage garage) {
        if (garage.getId() == null) {
            final String QUERY_SEQ = "SELECT last_value FROM garage_id_seq";
            garage.setId(jdbcTemplate.queryForObject(QUERY_SEQ, Long.class) + 1);

            final String QUERY = "INSERT INTO garage(name) VALUES (?)";
            jdbcTemplate.update(QUERY, garage.getName());
        }

        garage.getCars().forEach(car -> {
            car.setGarage(garage);
            save(car);
        });

        getCarsByGarage(garage)
                .stream()
                .filter(car -> !garage.getCars()
                        .stream()
                        .map(Car::getId)
                        .collect(Collectors.toList())
                        .contains(car.getId()))
                .forEach(this::remove);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Mark mark) {
        if (mark.getId() == null) {
            final String QUERY_SEQ = "SELECT last_value FROM mark_id_seq";
            mark.setId(jdbcTemplate.queryForObject(QUERY_SEQ, Long.class) + 1);

            final String QUERY = "INSERT INTO mark(mark_code) VALUES (?)";
            jdbcTemplate.update(QUERY, mark.getMarkCode());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Model model) {
        save(model.getMark());
        if (model.getId() == null) {
            final String QUERY_SEQ = "SELECT last_value FROM model_id_seq";
            model.setId(jdbcTemplate.queryForObject(QUERY_SEQ, Long.class) + 1);

            final String QUERY = "INSERT INTO model(mark_id, model_code) VALUES (?, ?)";
            jdbcTemplate.update(QUERY, model.getMark().getId(), model.getModelCode());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Car car) {
        save(car.getModel());
        if (car.getId() == null) {
            final String QUERY_SEQ = "SELECT last_value FROM car_id_seq";
            car.setId(jdbcTemplate.queryForObject(QUERY_SEQ, Long.class) + 1);

            final String QUERY = "INSERT INTO car(model_id, garage_id, number) VALUES (?, ?, ?)";
            jdbcTemplate.update(QUERY, car.getModel().getId(), car.getGarage().getId(), car.getNumber());
        }
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

    @Override
    public void remove(Car car) {
        final String QUERY = "DELETE FROM car WHERE id = ?";
        jdbcTemplate.update(QUERY, car.getId());
    }
}
