package com.example.deneme.Student;

import com.google.gson.JsonElement;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
@Entity
@Table
@SequenceGenerator(name = "ogrenci_seq", sequenceName = "ogrenci_sequence", allocationSize = 1)
public class Ogrenci {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ogrenci_seq")
    private Long id;
    private  String name;
    private String email;
    private LocalDate dob;
    @Transient
    private int age;

    public Ogrenci() {
    }

    public Ogrenci(String name , String email, LocalDate dob) {
        this.name = name ;
        this.email = email;
        this.dob = dob;

    }



    public static void add(Ogrenci newOgrenci) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Transient
    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Ogrenci{" +
                "id=" + id + '\n'+
                ", name='" + name + '\n' +
                ", email='" + email + '\n' +
                ", dob=" + dob + '\n'+
                ", age=" + age + '\n' +
                '}';
    }

    public Ogrenci(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;

    }

    public void setCreateDate(Date date) {
    }


    public OgrenciController fromJson(String buildResponseBody, Class<JsonElement> jsonElementClass) {
        return null;
    }

    public void setVersion(String version) {
    }
}

