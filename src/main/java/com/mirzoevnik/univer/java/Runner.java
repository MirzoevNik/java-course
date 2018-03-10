package com.mirzoevnik.univer.java;

import com.mirzoevnik.univer.java.task2.Task2Runner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mirzoevnik
 */
@SpringBootApplication
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);

        //Task2Runner.run();
    }
}
