package ru.spbstu.opd.business.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;
    private String phone;

    @ManyToMany
    private List<Lector> coursesList;

    @OneToMany
    private List<Homework> homeworkList;
}
