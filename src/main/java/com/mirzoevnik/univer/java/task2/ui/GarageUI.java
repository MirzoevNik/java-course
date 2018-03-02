package com.mirzoevnik.univer.java.task2.ui;

import com.mirzoevnik.univer.java.task2.domain.Car;
import com.mirzoevnik.univer.java.task2.domain.Garage;
import com.mirzoevnik.univer.java.task2.exception.GarageException;
import com.mirzoevnik.univer.java.task2.service.GarageService;
import com.mirzoevnik.univer.java.task2.service.GarageServiceImpl;
import com.mirzoevnik.univer.java.task2.util.JSONUtils;

import java.util.Scanner;

/**
 * @author mirzoevnik
 */
public class GarageUI {

    public static final String FILE_NAME = "cars.json";
    private GarageService garageService;
    private Garage garage;

    public GarageUI(Garage garage) {
        this.garage = garage;
    }

    private GarageService getGarageService() {
        if (garageService == null) {
            garageService = new GarageServiceImpl();
        }
        return garageService;
    }

    public void run() {
        showMenu();
        while (true) {
            System.out.print("Enter command: ");
            String command = new Scanner(System.in).next();
            executeCommand(command);
            if (command.equals("4")) {
                break;
            }
        }
    }

    private void executeCommand(String command) {
        switch (command) {
            case "1": showGarage(); break;
            case "2": addCarToGarage(); break;
            case "3": removeCarFromGarage(); break;
            case "4": exitFromGarage(); break;
            default: {
                System.out.println("Incorrect command! Retry, please.");
                showMenu();
            }
        }
    }

    /**
     * Показать меню
     */
    private void showMenu() {
        System.out.println("1. Посмотреть гараж");
        System.out.println("2. Добавить машину в гараж");
        System.out.println("3. Убрать машину из гаража");
        System.out.println("4. Выход");
    }

    /**
     * Посмотреть гараж
     */
    private void showGarage() {
        if (garage.getCars().isEmpty()) {
            System.out.println("Garage is empty");
        } else {
            System.out.println(garage);
            System.out.println("Count of cars: " + garage.getCars().size());
        }
    }

    /**
     * Добавить машину в гараж
     */
    private void addCarToGarage() {
        System.out.println(String.format("List of cars: %s", garage.getCars()));
        Car car = enterCar();
        try {
            getGarageService().addCar(garage, car);
        } catch (GarageException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Убрать машину из гаража
     */
    private void removeCarFromGarage() {
        System.out.println("Please, enter car number:");
        String carNumber = new Scanner(System.in).next();
        try {
            getGarageService().removeCar(garage, carNumber);
        } catch (GarageException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Выход
     */
    private void exitFromGarage() {
        System.out.println("Goodbye!");
        save();
    }

    private void save() {
        JSONUtils.write(FILE_NAME, garage);
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
