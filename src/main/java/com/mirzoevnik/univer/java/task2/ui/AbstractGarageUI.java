package com.mirzoevnik.univer.java.task2.ui;

import java.util.Scanner;

/**
 * @author mirzoevnik
 */
public abstract class AbstractGarageUI {

    /**
     * Посмотреть гараж
     */
    protected abstract void showGarage();

    /**
     * Добавить машину в гараж
     */
    protected abstract void addCarToGarage();

    /**
     * Убрать машину из гаража
     */
    protected abstract void removeCarFromGarage();

    /**
     * Сохранить
     */
    protected abstract void save();

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
     * Выход
     */
    private void exitFromGarage() {
        System.out.println("Goodbye!");
        save();
    }
}
