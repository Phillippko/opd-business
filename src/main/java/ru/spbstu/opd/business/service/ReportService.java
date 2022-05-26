package ru.spbstu.opd.business.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

import ru.spbstu.opd.business.Pair;
import ru.spbstu.opd.business.ReportType;
import ru.spbstu.opd.business.model.Homework;
import ru.spbstu.opd.business.model.Lection;
import ru.spbstu.opd.business.model.LectionReport;
import ru.spbstu.opd.business.model.Lector;
import ru.spbstu.opd.business.model.StudentReport;
import ru.spbstu.opd.business.model.Student;
import ru.spbstu.opd.business.util.CourseUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class ReportService {

    public String generateStudentReport(Student student, List<Lection> lections, ReportType mapperType) {
        StudentReport report = generateStudentReport(student, lections);
        try {
            return Objects.requireNonNull(mapperType.getMapper()).writeValueAsString(report);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String generateLectionReport(Lection lection, ReportType mapperType) {
        LectionReport report = generateStudentReport(lection);
        try {
            return mapperType.getMapper().writeValueAsString(report);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private LectionReport generateStudentReport(Lection lection) {
        List<Pair<String, String>> studentMarkPairs = lection.getStudentList().stream()
                .map(student -> getStudentMark(student, lection, student.getName()))
                .toList();
        return new LectionReport(lection.getName(), studentMarkPairs);
    }

    StudentReport generateStudentReport(Student student, List<Lection> lections) {
        Map<Lector, List<Lection>> studentCourses = CourseUtil.getCourseToLection(lections);
        studentCourses = CourseUtil.getStudentCourses(student, studentCourses);

        List<StudentReport.CourseMarksPair> studentMarks = studentCourses.entrySet().stream()
                .map(x -> getStudentMarks(student, x)).toList();

        return new StudentReport(student.getName(), studentMarks);
    }

    private StudentReport.CourseMarksPair getStudentMarks(Student student, Map.Entry<Lector, List<Lection>> lections) {
        return new StudentReport.CourseMarksPair(
                lections.getKey(),
                lections.getValue().stream()
                        .map(x -> getStudentMark(student, x, x.getName()))
                        .collect(Collectors.toList()));
    }

    private Pair<String, String> getStudentMark(Student student, Lection lection, String name) {
        if (!lection.getStudentList().contains(student))
            return Pair.of(name, "Н");

        Optional<Homework> homeworkOpt = student.getHomeworkList().stream()
                .filter(x -> x.getLection().equals(lection))
                .findFirst();
        if (homeworkOpt.isEmpty())
            return Pair.of(name, "0");

        return Pair.of(name, String.valueOf(homeworkOpt.get().getMark()));
    }
}
