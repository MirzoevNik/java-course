package com.mirzoevnik.univer.java.task3.ui;

import com.mirzoevnik.univer.java.task2.ui.AbstractGarageUI;
import com.mirzoevnik.univer.java.task3.domain.Car;
import com.mirzoevnik.univer.java.task2.exception.GarageException;
import com.mirzoevnik.univer.java.task3.domain.Garage;
import com.mirzoevnik.univer.java.task3.domain.Mark;
import com.mirzoevnik.univer.java.task3.domain.Model;
import com.mirzoevnik.univer.java.task3.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @author mirzoevnik
 */
//@Component
public class GarageUI extends AbstractGarageUI {

    private final GarageService garageService;

    private Garage garage;

    @Autowired
    public GarageUI(GarageService garageService) {
        this.garageService = garageService;
        loadGarage();
        run();
    }

    private void loadGarage() {
        if (garage == null) {
            garage = garageService.loadGarage();
        }
    }

    @Override
    protected void showGarage() {
        if (garage.getCars().isEmpty()) {
            System.out.println("Garage is empty");
        } else {
            System.out.println(garage);
            System.out.println("Count of cars: " + garage.getCars().size());
        }
    }

    @Override
    protected void addCarToGarage() {
        System.out.println(String.format("List of cars: %s", garage.getCars()));
        Car car = enterCar();
        try {
            garageService.addCar(garage, car);
        } catch (GarageException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void removeCarFromGarage() {
        System.out.println("Please, enter car number:");
        String carNumber = new Scanner(System.in).next();
        try {
            garageService.removeCar(garage, carNumber);
        } catch (GarageException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void save() {
        garageService.save(garage);
    }

    private Car enterCar() {
        System.out.println("Please, enter car data:");
        Scanner scanner = new Scanner(System.in);
        Car car = new Car();
        System.out.print("Mark: ");
        Mark mark = new Mark();
        mark.setMarkCode(scanner.next());
        System.out.print("Model: ");
        Model model = new Model();
        model.setMark(mark);
        model.setModelCode(scanner.next());
        car.setModel(model);
        System.out.print("Number: ");
        car.setNumber(scanner.next());
        return car;
    }
}
