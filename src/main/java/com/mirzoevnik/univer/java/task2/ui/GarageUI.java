package com.mirzoevnik.univer.java.task2.ui;

import com.mirzoevnik.univer.java.task2.domain.Car;
import com.mirzoevnik.univer.java.task2.domain.Garage;
import com.mirzoevnik.univer.java.task2.exception.GarageException;
import com.mirzoevnik.univer.java.task2.service.GarageService;
import com.mirzoevnik.univer.java.task2.service.GarageServiceImpl;

import java.util.Scanner;

/**
 * @author mirzoevnik
 */
public class GarageUI extends AbstractGarageUI {

    private GarageService garageService;
    private Garage garage;

    public GarageUI() {
        loadGarage();
    }

    private void loadGarage() {
        if (garage == null) {
            garage = getGarageService().loadGarage();
        }
    }

    private GarageService getGarageService() {
        if (garageService == null) {
            garageService = new GarageServiceImpl();
        }
        return garageService;
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
            getGarageService().addCar(garage, car);
        } catch (GarageException ex) {
            System.out.println(ex.getMessage());
        }
    }


    protected void removeCarFromGarage() {
        System.out.println("Please, enter car number:");
        String carNumber = new Scanner(System.in).next();
        try {
            getGarageService().removeCar(garage, carNumber);
        } catch (GarageException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected void save() {
        getGarageService().save(garage);
    }

    private Car enterCar() {
        System.out.println("Please, enter car data:");
        Scanner scanner = new Scanner(System.in);
        Car car = new Car();
        System.out.print("Mark: ");
        car.setMark(scanner.next());
        System.out.print("Model: ");
        car.setModel(scanner.next());
        System.out.print("Number: ");
        car.setNumber(scanner.next());
        return car;
    }
}
