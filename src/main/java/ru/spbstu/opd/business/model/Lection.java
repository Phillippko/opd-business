package ru.spbstu.opd.business.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@Data
public class Lection {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Lector lector;

    @ManyToMany
    private List<Student> studentList;

}
