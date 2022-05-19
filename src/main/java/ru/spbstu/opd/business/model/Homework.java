package ru.spbstu.opd.business.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Homework {
    @Id
    @GeneratedValue
    private Long id;

    private int mark;

    @ManyToOne
    Student student;

    @ManyToOne
    Lection lection;

}
