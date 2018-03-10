package com.mirzoevnik.univer.java.task3.mapper;

import com.mirzoevnik.univer.java.task3.domain.Mark;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

/**
 * @author mirzoevnik
 */
public class MarkMapper implements RowMapper<Mark> {

    @Override
    @SneakyThrows
    public Mark mapRow(ResultSet rs, int rowNum) {
        Mark mark = new Mark();
        mark.setId(rs.getLong("id"));
        mark.setMarkCode(rs.getString("mark_code"));
        return mark;
    }
}
