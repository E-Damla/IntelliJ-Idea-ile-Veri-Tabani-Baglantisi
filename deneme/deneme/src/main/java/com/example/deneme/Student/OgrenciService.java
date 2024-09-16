package com.example.deneme.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service

public class OgrenciService {

    public final OgrenciRepository studentRepository;

    @Autowired
    public OgrenciService(OgrenciRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addNewOgrenci(Ogrenci student) {
        Optional<Ogrenci> studentOptional = OgrenciRepository
                .findOgrenciByEmail(student.getEmail());
        if (studentOptional != null) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);

    }


    public List<Ogrenci> getOgrenci() {
        return studentRepository.findAll();
    }


    public void deleteOgrenci(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if (!exist) {
            throw new IllegalStateException(
                    "student with id" + studentId + "does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    public void updateOgrenci(Long studentId, String name, String email) {
        Ogrenci student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id"+ studentId+" does not exist"));
        if ((name != null) &&
                name.length() > 0 &&
                !Objects.equals(student.getName(),name)) {
            student.setName(name);
        }
        if (email != null &&
                email.length()>0 &&
                !Objects.equals(student.getEmail(),email)) {
            Optional<Ogrenci> studentOptional= OgrenciRepository
                    .findOgrenciByEmail(email);
            if( studentOptional.isPresent()) { throw new IllegalStateException("email taken");
            }
        }
    }
}

