package com.jakub.ajamarks.services.showdataservices;

import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by ja on 12.01.17.
 */
@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    ClassroomRepository classroomRepository;

    public Classroom saveClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public Classroom updateByClassroomNumber(Classroom classroom) {
        Classroom byClassroomName = classroomRepository.findByClassroomName(classroom.getClassroomName());
        classroom.setIdClassroom(byClassroomName.getIdClassroom());
        return classroomRepository.save(classroom);
    }

    public void delete(Classroom classroom) {
        classroomRepository.delete(classroom);
    }

    public List<Classroom> getAll() {
        return classroomRepository.findAllByOrderByClassroomNameAsc();
    }


    public Classroom getClassroomById(long id) {
        return classroomRepository.findOne(id);
    }

    public Classroom getClassroomByNumber(int classNumber) {
        return classroomRepository.findByClassroomNumber(classNumber);
    }

    public Classroom getClassroomByName(String name) {
        return classroomRepository.findByClassroomName(name);
    }

    public Set<Student> getClassroomStudentsByClassroomNumber(int classNumber) {
        Classroom byClassroomNumber = classroomRepository.findByClassroomNumber(classNumber);
        if (byClassroomNumber==null)
            return Collections.emptySet();
        else {
            Set<Student> studentsInClassroom = byClassroomNumber.getStudentsInClassroom();
            if (studentsInClassroom.size() > 0)
                return studentsInClassroom;
            else
                return Collections.emptySet();
        }
    }

    public Set<Student> getClassroomStudentsByClassroomName(String className) {
        Classroom byClassroomName = classroomRepository.findByClassroomName(className);
        if (byClassroomName == null)
            return Collections.emptySet();
        else {
            Set<Student> studentsInClassroom = byClassroomName.getStudentsInClassroom();
            if (studentsInClassroom.size() > 0)
                return studentsInClassroom;
            else
                return Collections.emptySet();
        }
    }
}
