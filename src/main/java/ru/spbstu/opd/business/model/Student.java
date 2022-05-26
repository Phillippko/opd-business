package ru.spbstu.opd.business.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student {
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String email;
    private String phone;

    private List<Lector> coursesList;

    private List<Homework> homeworkList;

}
