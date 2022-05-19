package ru.spbstu.opd.business.util;

import ru.spbstu.opd.business.model.Lection;
import ru.spbstu.opd.business.model.Lector;
import ru.spbstu.opd.business.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CourseUtil {
    public static Map<Lector, List<Lection>> getCourseToLection(List<Lection> lectionList) {
        return lectionList.stream()
                .collect(Collectors.groupingBy(Lection::getLector));
    }

    public static Map<Lector, List<Lection>> getStudentCourses(Student student, Map<Lector, List<Lection>> courses) {
        return courses.entrySet().stream()
                .filter(course -> student.getCoursesList().contains(course.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}