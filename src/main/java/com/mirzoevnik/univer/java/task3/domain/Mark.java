package com.mirzoevnik.univer.java.task3.domain;

import lombok.Data;
import lombok.ToString;

/**
 * @author mirzoevnik
 */
@Data
@ToString(exclude = "id")
public class Mark {

    private Long id;

    private String markCode;
}
