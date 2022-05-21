package ru.spbstu.opd.business.model;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private Long id;

    private String name;

    private String email;
    private String phone;

    private List<Lector> coursesList;

    private List<Homework> homeworkList;
}
