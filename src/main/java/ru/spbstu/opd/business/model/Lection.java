package ru.spbstu.opd.business.model;


import lombok.Data;


import java.util.List;

@Data
public class Lection {
    private Long id;

    private String name;

    private Lector lector;

    private List<Student> studentList;

}
