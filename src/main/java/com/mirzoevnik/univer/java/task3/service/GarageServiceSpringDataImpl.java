package com.mirzoevnik.univer.java.task3.service;

import com.mirzoevnik.univer.java.task2.exception.GarageException;
import com.mirzoevnik.univer.java.task3.domain.Car;
import com.mirzoevnik.univer.java.task3.domain.Garage;
import com.mirzoevnik.univer.java.task3.domain.Mark;
import com.mirzoevnik.univer.java.task3.domain.Model;
import com.mirzoevnik.univer.java.task3.repository.CarRepository;
import com.mirzoevnik.univer.java.task3.repository.GarageRepository;
import com.mirzoevnik.univer.java.task3.repository.MarkRepository;
import com.mirzoevnik.univer.java.task3.repository.ModelRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mirzoevnik
 */
//@Transactional
@Service
@Primary
public class GarageServiceSpringDataImpl implements GarageService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private GarageRepository garageRepository;
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private ModelRepository modelRepository;

    @Override
    public Garage getGarageById(Long id) {
        return garageRepository.findById(id).orElse(new Garage());
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(new Car());
    }

    @Override
    public Model getModelById(Long id) {
        return modelRepository.findById(id).orElse(new Model());
    }

    @Override
    public Mark getMarkById(Long id) {
        return markRepository.findById(id).orElse(new Mark());
    }

    @Override
    public Garage loadGarage() {
        Iterable<Garage> garages = garageRepository.findAll();
        if (!garages.iterator().hasNext()) {
            Garage garage = new Garage();
            garage.setName("default");
            return garage;
        }
        Garage garage = garages.iterator().next();
        garage.setCars(getCarsByGarage(garage));
        return garage;
    }

    private List<Car> getCarsByGarage(Garage garage) {
        return carRepository.findByGarage(garage);
    }

    @Override
    public void save(Garage garage) {
        garageRepository.save(garage);

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

    @Override
    public void save(Mark mark) {
        markRepository.save(mark);
    }

    @Override
    public void save(Model model) {
        modelRepository.save(model);
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

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
        carRepository.delete(car);
    }
}
