package com.mirzoevnik.univer.java;

import com.mirzoevnik.univer.java.task2.domain.Garage;
import com.mirzoevnik.univer.java.task2.ui.GarageUI;

/**
 * @author mirzoevnik
 */
public class Runner {

    public static void main(String[] args) {
        Garage garage = getGarage();
        GarageUI garageUI = new GarageUI(garage);
        garageUI.run();
    }

    private static Garage getGarage() {
        return new Garage();
    }
}
