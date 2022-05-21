package ru.spbstu.opd.business.service;

import lombok.extern.slf4j.Slf4j;

import ru.spbstu.opd.business.Pair;
import ru.spbstu.opd.business.model.Homework;
import ru.spbstu.opd.business.model.Lection;
import ru.spbstu.opd.business.model.Lector;
import ru.spbstu.opd.business.model.Student;
import ru.spbstu.opd.business.util.CourseUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class SmsService {

    private static final long MIN_AVERAGE_MARK = 4;

    void checkMarks(List<Student> studentList, List<Lection> lectionList) {
        Map<Lector, List<Lection>> courses = CourseUtil.getCourseToLection(lectionList);
        studentList.forEach(student -> checkMarks(student, courses));
    }

    void checkMarks(Student student, Map<Lector, List<Lection>> courses) {
        CourseUtil.getStudentCourses(student, courses).entrySet().stream()
                .map(course -> Pair.of(
                                course.getKey(),
                                countAverageMark(student, course.getValue())
                        )
                )
                .filter(x -> x.getSecond() < MIN_AVERAGE_MARK)
                .forEach(x -> sendSms(student, x));
    }

    private void sendSms(Student student, Pair<Lector, Double> courseAverageMark) {
        log.info(
                "Sent SMS to {}, phone number {}, having {} avg mark in {} course",
                student.getName(),
                student.getPhone(),
                courseAverageMark.getSecond(),
                courseAverageMark.getFirst().getName()
                );
    }

    private double countAverageMark(Student student, List<Lection> lections) {
        long sum = student.getHomeworkList().stream()
                .collect(Collectors.summarizingInt(Homework::getMark)).getSum() ;
        return (double) sum / lections.size();
    }

}
