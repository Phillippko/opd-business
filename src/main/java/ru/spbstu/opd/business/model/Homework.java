package ru.spbstu.opd.business.model;

import lombok.Data;

@Data
public class Homework {
   private Long id;

    private int mark;

    Student student;

    Lection lection;

}
