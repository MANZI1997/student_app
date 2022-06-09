package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();

    }
    public void addStudent(Student student){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
                throw new IllegalStateException("this email already taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudentById(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("the student you're trying to delete whith this id"+ studentId +"does not exist" );
        }
        studentRepository.deleteById(studentId);
    }

   @Transactional
    public void updateStudentById(Long studentId, String name, String email) {
       Student student = studentRepository.findById(studentId)
               .orElseThrow(() -> new IllegalStateException("student with id :"+ ""+ studentId +"does not exist"));
       if(name !=null && name.length()>0 && !Objects.equals(student.getName(),name)){
           student.setName(name);
       }
       if(email !=null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
           Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);
           if(optionalStudent.isPresent()){
               throw new IllegalStateException("this email is taken ");
           }
           student.setEmail(email);

       }



    }
}
