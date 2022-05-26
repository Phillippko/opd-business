package ru.spbstu.opd.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lector {
    @EqualsAndHashCode.Include
    @JsonIgnore
    private Long id;

    private String name;
}
