package ru.spbstu.opd.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.spbstu.opd.business.Pair;


import java.util.List;

@AllArgsConstructor
@Data
public class StudentReport {
    private String studentName;
    private List<CourseMarksPair> courseMarksPairs;

    @AllArgsConstructor
    @Data
    public static class CourseMarksPair {
        private Lector lector;
        private List<Pair<String,String>> lectionMarkPairs;
    }

    @AllArgsConstructor
    @Data
    public static class LectionMarkPair {
        private String lectionName;
        private String lectionMark;

    }
}
