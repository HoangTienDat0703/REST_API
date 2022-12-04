package com.example.practive1.repository;

import com.example.practive1.entity.Student;
import com.example.practive1.request.InQueryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByFirstName(String firstName);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    List<Student> findByFirstNameIn(List<String> firstNames);
}
