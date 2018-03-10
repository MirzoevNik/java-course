package com.mirzoevnik.univer.java.task3.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author mirzoevnik
 */
@Data
@Entity
@Table(name = "model")
@ToString(exclude = "id")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_code")
    private String modelCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mark_id")
    private Mark mark;
}
