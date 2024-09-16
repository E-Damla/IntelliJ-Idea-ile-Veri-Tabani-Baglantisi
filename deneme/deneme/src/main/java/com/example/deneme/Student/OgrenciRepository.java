package com.example.deneme.Student;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository

public interface OgrenciRepository
        extends JpaRepository<Ogrenci,Long> {


    static Optional<Ogrenci> findOgrenciByEmail(String ignoredEmail) {

        return null;
    }

    @Query("SELECT s  FROM Ogrenci s WHERE s.email=:email")
    Ogrenci findStudentByEmail(String email);


    boolean existsById(Long studentId);
}