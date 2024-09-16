package com.example.deneme.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/students")

public class OgrenciController {
    public final OgrenciService studentService;
    public final Main main;
    private static final List<Ogrenci> Ogrenci = new ArrayList<>();
    public OgrenciController getAsOgrenciController;
    private OgrenciController asJsonObject;

    @Autowired
    public OgrenciController(OgrenciService studentService, Main main) {
        this.studentService = studentService;
        this.main = main;
    }

    @GetMapping
    public ResponseEntity<List<Ogrenci>> getOgrenci() {
        return new ResponseEntity<>(studentService.getOgrenci(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ogrenci> getOgrenci(@PathVariable Long id) {
        Ogrenci result = getOgrenciById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private Ogrenci getOgrenciById(Long id) {
        return Ogrenci.stream()
                .filter( Ogrenci ->Ogrenci.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("STUDENT NOT FOUND"));
    }

    @PutMapping("/{studentId}") //Listeye yeni veri eklemek istendiğinde kullanılır.//
    public ResponseEntity<Ogrenci> createOgrenci(@RequestBody Ogrenci newOgrenci) {
        newOgrenci.setCreateDate(new Date());
        Ogrenci.add(newOgrenci);
        studentService.addNewOgrenci(newOgrenci);
        Main.dene();
        return new ResponseEntity<>(newOgrenci, HttpStatus.CREATED);
    }

    @DeleteMapping("/{studentId}")
    public void deleteOgrenci(
            @PathVariable("studentId")Long studentId){
        studentService.deleteOgrenci(studentId);
    }
    @PutMapping
    public void updateOgrenci(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required=false) String name,
            @RequestParam(required=false) String email){
        studentService.updateOgrenci(studentId,name,email);

    }
    @Bean
    public HttpTraceRepository httpTraceRepository() {return new InMemoryHttpTraceRepository();}

    public OgrenciController getAsJsonObject() {
        return asJsonObject;
    }

    public void setAsJsonObject() {
        setAsJsonObject(null);
    }

    public void setAsJsonObject(OgrenciController asJsonObject) {
        this.asJsonObject = asJsonObject;
    }

    public boolean hashCode(String build) {
        return false;
    }

    public OgrenciController get(String build) {
        return null;
    }

    public String getAsString() {
        return null;
    }
    @Configuration

    public class HttpTraceActuatorConfiguration {

        @Bean
        public HttpTraceRepository httpTraceRepository() {
            return new InMemoryHttpTraceRepository();
        }

    }
}