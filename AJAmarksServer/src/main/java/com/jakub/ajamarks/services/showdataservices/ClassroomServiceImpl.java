package com.jakub.ajamarks.services.showdataservices;

import com.google.common.base.Preconditions;
import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Student;
import com.jakub.ajamarks.repositories.ClassroomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    ClassroomRepository classroomRepository;

    public Classroom saveClassroom(Classroom classroom) {
        Preconditions.checkArgument(classroom != null, "classroom can't be null");
        log.info("Classrom: {}", classroom);

        return classroomRepository.save(classroom);
    }

    public void delete(Classroom classroom) {
        Preconditions.checkArgument(classroom != null, "classroom can't be null");
        classroomRepository.delete(classroom);
    }

    @Override
    public Classroom updateClassroom(long classroomId, Classroom classroom) {
        Preconditions.checkArgument(classroomId > 0, "classroom identity number can not be les than 1");
        Preconditions.checkNotNull(classroom, "classroom can not be null");
        Classroom byClassroomName = classroomRepository.findOne(classroomId);
        classroom.setIdClassroom(byClassroomName.getIdClassroom());
        return classroomRepository.save(classroom);
    }

    public List<Classroom> getAllByClassroomNameAsc() {
        return classroomRepository.findAllByOrderByClassroomNameAsc();
    }


    public Classroom getClassroomById(long id) {
        Preconditions.checkArgument(id > 0, "Id number can't be less than 1");
        return classroomRepository.findOne(id);
    }

    public Classroom getClassroomByNumber(int classroomNumber) {
        Preconditions.checkArgument(classroomNumber > 0, "classroom number can't be less than 1");
        return classroomRepository.findByClassroomNumber(classroomNumber);
    }

    public Classroom getClassroomByName(String classroomName) {
        Preconditions.checkArgument(classroomName != null, "classroom name can't be null");
        return classroomRepository.findByClassroomName(classroomName);
    }

    public Set<Student> getClassroomStudentsByClassroomNumber(int classroomNumber) {
        Preconditions.checkArgument(classroomNumber > 0, "classroom number can't be less than 1");
        Classroom byClassroomNumber = classroomRepository.findByClassroomNumber(classroomNumber);
        if(byClassroomNumber==null)
            throw new NullPointerException("No such a classroom");
        Set<Student> studentsInClassroom = byClassroomNumber.getStudentsInClassroom();
        if (studentsInClassroom.size() > 0)
            return studentsInClassroom;
        else
            return Collections.emptySet();

    }

    public Set<Student> getClassroomStudentsByClassroomName(String classroomName) {
        Preconditions.checkArgument(classroomName != null, "classroom name can't be null");
        Classroom byClassroomName = classroomRepository.findByClassroomName(classroomName);
        if(byClassroomName==null)
            throw new NullPointerException("No such a classroom");
        Set<Student> studentsInClassroom = byClassroomName.getStudentsInClassroom();
        if (studentsInClassroom.size() > 0)
            return studentsInClassroom;
        else
            return Collections.emptySet();
    }

}
