package com.mirzoevnik.univer.java.task3.mapper;

import com.mirzoevnik.univer.java.task3.domain.Model;
import com.mirzoevnik.univer.java.task3.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author mirzoevnik
 */
@Repository
public class ModelMapper implements RowMapper<Model> {

    private GarageService garageService;

    @Autowired
    public ModelMapper(GarageService garageService) {
        this.garageService = garageService;
    }

    @Override
    public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
        Model model = new Model();
        model.setId(rs.getLong("id"));
        model.setMark(garageService.getMarkById(rs.getLong("mark_id")));
        model.setModelCode(rs.getString("model_code"));
        return model;
    }
}
