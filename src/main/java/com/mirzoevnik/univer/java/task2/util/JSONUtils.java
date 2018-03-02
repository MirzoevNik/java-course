package com.mirzoevnik.univer.java.task2.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mirzoevnik.univer.java.task2.domain.Garage;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author mirzoevnik
 */
public class JSONUtils {

    @SneakyThrows
    public static void write(final String fileName, Garage garage){
        Path filePath = Paths.get(fileName);
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        Files.write(filePath, getGson().toJson(garage).getBytes());
    }

    @SneakyThrows
    public static Garage read(final String fileName) {
        Path filePath = Paths.get(fileName);
        if (!Files.exists(filePath)) {
            return new Garage();
        }
        String data = new String(Files.readAllBytes(filePath));
        return getGson().fromJson(data, Garage.class);
    }

    private static Gson getGson() {
        return new GsonBuilder().create();
    }
}
