package ru.spbstu.opd.business.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Homework {
    @EqualsAndHashCode.Include

    private Long id;

    private int mark;

    Student student;

    Lection lection;

}
