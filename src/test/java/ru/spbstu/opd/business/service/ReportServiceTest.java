package ru.spbstu.opd.business.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.spbstu.opd.business.model.Homework;
import ru.spbstu.opd.business.model.Lection;
import ru.spbstu.opd.business.model.Lector;
import ru.spbstu.opd.business.model.Student;

import java.util.List;

class ReportServiceTest {

    private static final Homework TEST_HOMEWORK = new Homework();
    private static final Lector TEST_LECTOR = new Lector();
    private static final Student TEST_STUDENT = new Student();
    private static final Lection TEST_LECTION = new Lection();

    private ReportService reportService;

    @BeforeAll
    private static void setUp(){
        configureLector();
        configureLection();
        configureHomework();
        configureStudent();
    }

    @Test
    void testGenerateJsonReport() {
        reportService = new ReportService();
        List<Lection> lections = getLections();
        String report = reportService.generateJsonReport(TEST_STUDENT, lections);

    }

    @Test
    void testGenerateXmlReport() {
        reportService = new ReportService();
        List<Lection> lections = getLections();
        String report = reportService.generateXmlReport(TEST_STUDENT, lections);
    }

    private List<Lection> getLections() {
    return List.of(TEST_LECTION);
    }

    private static void configureStudent() {
        Student student = TEST_STUDENT;
        student.setName("TEST");
        student.setHomeworkList(getHomeworks());
        student.setCoursesList(getLectors());
    }

    private static List<Lector> getLectors() {
        return List.of(TEST_LECTOR);
    }

    private static void configureLector() {
        TEST_LECTOR.setName("TEST LECTOR");
    }

    private static List<Homework> getHomeworks() {
        return List.of(TEST_HOMEWORK);
    }

    private static Homework configureHomework() {
        Homework homework = TEST_HOMEWORK;
        homework.setLection(TEST_LECTION);
        homework.setStudent(TEST_STUDENT);
        homework.setMark(5);
        return homework;
    }

    private static void configureLection() {
        Lection lection = TEST_LECTION;
        lection.setLector(TEST_LECTOR);
        lection.setStudentList(getStudents());
        lection.setName("TEST_LECTION_NAME");
    }

    private static List<Student> getStudents() {
        return List.of(TEST_STUDENT);
    }
}