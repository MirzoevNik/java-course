package com.mirzoevnik.univer.java.task4.controller;

import com.mirzoevnik.univer.java.task3.domain.Car;
import com.mirzoevnik.univer.java.task3.domain.Garage;
import com.mirzoevnik.univer.java.task3.domain.Mark;
import com.mirzoevnik.univer.java.task3.service.GarageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

/**
 * @author mirzoevnik
 */
@Controller
public class GarageController {

    private final GarageService garageService;

    private Garage garage;

    @Autowired
    public GarageController(GarageService garageService) {
        this.garageService = garageService;
        loadGarage();
    }

    private void loadGarage() {
        if (garage == null) {
            garage = garageService.loadGarage();
        }
    }

    @GetMapping("/garage")
    public String garage(Model model) {
        model.addAttribute("name", garage.getName());
        if (garage.getCars().isEmpty()) {
            model.addAttribute("count", "Garage is empty");
            model.addAttribute("cars", Collections.EMPTY_LIST);
        } else {
            model.addAttribute("count", "Count of cars: " + garage.getCars().size());
            model.addAttribute("cars", garage.getCars());
        }
        return "garage";
    }

    @PostMapping("/garage")
    @SneakyThrows
    public String save() {
        garageService.save(garage);
        return "redirect:/garage";
    }

    @GetMapping("/add-car")
    public String addCar(Model model) {
        Car car = new Car();
        Mark mark = new Mark();
        com.mirzoevnik.univer.java.task3.domain.Model modelDomain = new com.mirzoevnik.univer.java.task3.domain.Model();
        modelDomain.setMark(mark);
        car.setModel(modelDomain);
        model.addAttribute("car", car);
        return "add-car";
    }

    @PostMapping("/add-car")
    @SneakyThrows
    public String addCarAction(@ModelAttribute Car car) {
        garageService.addCar(garage, car);
        return "redirect:/garage";
    }

    @GetMapping("/remove-car")
    public String removeCar(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "remove-car";
    }

    @PostMapping("/remove-car")
    @SneakyThrows
    public String removeCarAction(@ModelAttribute Car car) {
        garageService.removeCar(garage, car.getNumber());
        return "redirect:/garage";
    }
}
