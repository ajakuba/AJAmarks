package com.jakub.ajamarks.services.statisticservices;

import com.google.common.base.Preconditions;
import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.ClassroomRepository;
import com.jakub.ajamarks.repositories.MarkRepository;
import com.jakub.ajamarks.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by ja on 16.01.17.
 */

@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    MarkRepository markRepository;
    @Autowired
    ClassroomRepository classroomRepository;

    @Override
    public Double countAverageMarkForUser(String userName) {
        Preconditions.checkArgument(userName!=null, "student user name can't be null");
        return studentRepository.findByUserName(userName).getMarkList()
                .stream()
                .mapToInt(Mark::getMarkValue)
                .average()
                .orElse(0);
    }

    @Override
    public Double countAverageMarkForClassroom(String classroomName) {
        Preconditions.checkArgument(classroomName!=null, "classroom name can't be null");
        List<Mark> markList = new ArrayList<>();
        List<Student> byClassroom = studentRepository.findByClassroomClassroomName(classroomName);
        byClassroom.stream().forEach(student -> markList.addAll(student.getMarkList()));

        return markList
                .stream()
                .mapToInt(Mark::getMarkValue)
                .average()
                .orElse(0);
    }

    @Override
    public Double countAverageMarkForSchool() {
        List<Mark> markList = new ArrayList<>();
        List<Student> studentRepositoryAll = studentRepository.findAll();
        studentRepositoryAll.forEach(student -> markList.addAll(student.getMarkList()));
        return markList
                .stream()
                .mapToInt(Mark::getMarkValue)
                .average()
                .orElse(0);
    }

    @Override
    public Map<Double, TreeSet<Student>> sortStudentsByAverageAsc() {
        Map<Double, TreeSet<Student>> doubleStudentMap = new TreeMap<>();
        List<Student> studentRepositoryAll = studentRepository.findAll();
        studentRepositoryAll.removeIf(student -> student.getMarkList().isEmpty());

        studentRepositoryAll.forEach(student -> {
            double average = student.getMarkList().stream().mapToInt(Mark::getMarkValue).average().orElse(0);
            if (!doubleStudentMap.containsKey(average)) {
                TreeSet<Student> students = new TreeSet<>();
                students.add(student);
                doubleStudentMap.put(average, students);
            } else {
                doubleStudentMap.get(average).add(student);
            }
        });
        return doubleStudentMap;
    }

    @Override
    public boolean isStudentAverageHigherThenInClassroom(String userName) {
        Preconditions.checkArgument(userName!=null, "student user name can't be null");
        Student student = studentRepository.findByUserName(userName);
        Classroom classroom = student.getClassroom();

        Double classroomAverage = countAverageMarkForClassroom(classroom.getClassroomName());
        Double studentAverage = countAverageMarkForUser(userName);

        return classroomAverage >= studentAverage ? false : true;
    }

    public boolean isStudentAverageHigherThenInSchool(String userName) {
        Double schoolAverage = countAverageMarkForSchool();
        Double studentAverage = countAverageMarkForUser(userName);

        return schoolAverage >= studentAverage ? false : true;
    }

    @Override
    public List<Student> getStudentsAboveSchoolAverage() {

        List<Student> studentAboveAverageList = new ArrayList<>();

        Double schoolAverage = countAverageMarkForSchool();

        studentRepository.findAll().forEach(student -> {
            double average = countAverageMarkForUser(student.getUserName());
            if (average > schoolAverage) {
                studentAboveAverageList.add(student);
            }
        });
        return studentAboveAverageList;
    }

    @Override
    public List<Student> getStudentsAboveStudentAverage(String userName) {
        Double studentAverage = countAverageMarkForUser(userName);
        List<Student> studentRepositoryAll = studentRepository.findAll();
        studentRepositoryAll.remove(studentRepository.findByUserName(userName));
        studentRepositoryAll.removeIf(student -> student.getMarkList().isEmpty());
        List<Student> studentsAboveAverage = studentRepositoryAll
                .stream()
                .filter(student -> countAverageMarkForUser(student.getUserName()) > studentAverage)
                .collect(Collectors.toList());

        return studentsAboveAverage;
    }


    @Override
    public int countHowManyStudentsAboveSchoolAverage() {
        List<Double> studentsAverageList = new ArrayList<>();

        studentRepository.findAll().forEach(student ->
                studentsAverageList.add(countAverageMarkForUser(student.getUserName())));

        double average = studentsAverageList.stream().mapToDouble(Double::doubleValue).average().orElse(0);

        long numberOfStudentsAboveAverage = studentsAverageList.stream().filter(aDouble -> aDouble > average).count();

        return (int) numberOfStudentsAboveAverage;

    }

    @Override
    public int countHowManyStudentsAboveStudentAverage(String userName) {
        Preconditions.checkArgument(userName!=null, "student user name can't be null");
        List<Double> studentsAverageList = new ArrayList<>();

        studentRepository.findAll().forEach(student ->
                studentsAverageList.add(countAverageMarkForUser(student.getUserName())));

        double userAverage = countAverageMarkForUser(userName);

        long numberOfStudentsAboveUserAverage = studentsAverageList
                .stream()
                .filter(aDouble -> aDouble > userAverage)
                .count();

        return (int) numberOfStudentsAboveUserAverage;
    }

    @Override
    public Map<Mark, Integer> countQuantityOfEachMarkForSchool() {
        Map<Mark, Integer> markIntegerMap = new HashMap<>();

        markRepository.findAll().forEach(mark -> markIntegerMap.put(mark, 0));

        List<Student> studentRepositoryAll = studentRepository.findAll();
        studentRepositoryAll.removeIf(student -> student.getMarkList().isEmpty());

        studentRepositoryAll.forEach(student -> student.getMarkList().forEach(mark -> {
            Integer integer = markIntegerMap.get(mark);
            int counter = counter(integer);
            markIntegerMap.put(mark, counter);
        }));

        return markIntegerMap;
    }

    @Override
    public Map<Mark, Set<Student>> getFromClassroomMarkStudentListMap(String classroomName) {
        Preconditions.checkArgument(classroomName!=null, "classroom name can't be null" );
        Map<Mark, Set<Student>> integerStudentMap = new TreeMap<>();

        List<Mark> markList = markRepository.findAll();
        markList.forEach(mark -> integerStudentMap.put(mark, new TreeSet<>()));

        Classroom byClassroomNumber = classroomRepository.findByClassroomName(classroomName);
        Set<Student> studentsInClassroom = byClassroomNumber.getStudentsInClassroom();

        studentsInClassroom.forEach(student -> student.getMarkList().forEach(mark -> {
            if (!integerStudentMap.containsKey(mark)) {
                Set<Student> students = new TreeSet<>();
                students.add(student);
                integerStudentMap.put(mark, students);
            } else {
                integerStudentMap.get(mark).add(student);
            }
        }));

        return integerStudentMap;
    }

    @Override
    public Map<Mark, Set<Student>> getFromSchoolMarkStudentListMap() {
        Map<Mark, Set<Student>> integerStudentMap = new TreeMap<>();
        List<Mark> markList = markRepository.findAll();
        markList.forEach(mark -> integerStudentMap.put(mark, new TreeSet<>()));

        List<Student> studentRepositoryAll = studentRepository.findAll();

        studentRepositoryAll.removeIf(student -> student.getMarkList().isEmpty());

        studentRepositoryAll
                .forEach(student -> student.getMarkList()
                        .forEach(mark -> {
                            if (!integerStudentMap.containsKey(mark)) {
                                Set<Student> students = new TreeSet<>();
                                students.add(student);
                                integerStudentMap.put(mark, students);
                            } else {
                                integerStudentMap.get(mark).add(student);
                            }

                        }));

        return integerStudentMap;

    }

    private int counter(int i) {
        i++;
        return i;
    }
}
