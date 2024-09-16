package com.example.deneme.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@Configuration
public class OgrenciConfig {
    @Bean
    CommandLineRunner commandLineRunner( OgrenciRepository repository) {
        return args-> {
            Ogrenci vnbxm = new Ogrenci(
                    1L,
                    "vnbxm",
                    "bvmxn@gmail.com",
                    LocalDate.of(2000, Month.DECEMBER,5)
            );
            Ogrenci nmnmv = new Ogrenci(
                    "nmnmv",
                    "nmnmv@gmail.com",
                    LocalDate.of(2002,Month.AUGUST ,7)
            );
            Ogrenci aa = new Ogrenci(
                    "aa",
                    "aa@gmail.com",
                    LocalDate.of(2009,Month.AUGUST ,7)
            );
            Ogrenci bb = new Ogrenci(
                    "bb",
                    "bb@gmail.com",
                    LocalDate.of(2006,Month.AUGUST ,7)
            );
            repository.saveAll(
                    List.of( vnbxm,nmnmv,aa,bb)
            );

        };
    }
}

