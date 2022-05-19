package ru.spbstu.opd.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Lector {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    private String name;
}
