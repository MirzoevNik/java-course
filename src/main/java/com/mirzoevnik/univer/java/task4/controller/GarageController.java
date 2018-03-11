package com.mirzoevnik.univer.java.task4.controller;

import com.mirzoevnik.univer.java.task3.domain.Garage;
import com.mirzoevnik.univer.java.task3.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        } else {
            model.addAttribute("count", "Count of cars: " + garage.getCars().size());
        }
        return "garage";
    }
}
