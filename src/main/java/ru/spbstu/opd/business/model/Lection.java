package ru.spbstu.opd.business.model;


import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lection {
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private Lector lector;

    private List<Student> studentList;

}
