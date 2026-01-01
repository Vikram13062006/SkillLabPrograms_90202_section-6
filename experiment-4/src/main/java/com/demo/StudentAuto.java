package com.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "student_auto")
public class StudentAuto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public StudentAuto() {
    }

    public StudentAuto(String name) {
        this.name = name;
    }
}