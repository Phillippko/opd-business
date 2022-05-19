package ru.spbstu.opd.business.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import ru.spbstu.opd.business.model.Lection;
import ru.spbstu.opd.business.model.Lector;
import ru.spbstu.opd.business.model.Student;
import ru.spbstu.opd.business.util.CourseUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class EmailService {
    private static final long MAX_MISSED_LECTIONS = 3;

    void checkAttending(List<Student> studentList, List<Lection> lectionList) {
        Map<Lector, List<Lection>> courses = CourseUtil.getCourseToLection(lectionList);
        studentList.forEach(student -> checkAttending(student, courses));
    }


    void checkAttending(Student student, Map<Lector, List<Lection>> courses) {
        courses.entrySet().stream()
                .filter(course -> student.getCoursesList().contains(course.getKey()))
                .map(course -> Pair.of(
                        course.getKey(),
                        countMissedLections(student, course.getValue())
                        )
                )
                .filter(x -> x.getSecond() > MAX_MISSED_LECTIONS)
                .forEach(x -> sendEmail(student, x));
    }

    private void sendEmail(Student student, Pair<Lector, Long> courseMissedPair) {
        log.info(
                "Sent email to {}, email {}, having {} missed lections in {} course",
                student.getName(),
                student.getEmail(),
                courseMissedPair.getSecond(),
                courseMissedPair.getFirst().getName()
        );
        log.info(
                "Sent email to lector {}, email {}, having {} missed lections by student {}",
                courseMissedPair.getFirst().getName(),
                student.getEmail(),
                courseMissedPair.getSecond(),
                student.getName()
        );
    }

    private long countMissedLections(Student student, List<Lection> lections) {
        return lections.stream()
                .filter(x -> !x.getStudentList().contains(student)).count();
    }

}
