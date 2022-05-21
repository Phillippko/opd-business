package ru.spbstu.opd.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.spbstu.opd.business.Pair;

import java.util.List;

@AllArgsConstructor
@Data
public class LectionReport {
    private String lectionName;
    private List<Pair<String, String>> lectionMarkPairs;

}
