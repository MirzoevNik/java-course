package com.mirzoevnik.univer.java.task3.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author mirzoevnik
 */
@Data
@Entity
@Table(name = "mark")
@ToString(exclude = "id")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mark_code")
    private String markCode;
}
