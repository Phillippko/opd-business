package ru.spbstu.opd.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Lector {
    @JsonIgnore
    private Long id;

    private String name;
}
