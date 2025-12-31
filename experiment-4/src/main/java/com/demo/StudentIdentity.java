package com.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "student_identity")
public class StudentIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public StudentIdentity() {
    }

    public StudentIdentity(String name) {
        this.name = name;
    }
}