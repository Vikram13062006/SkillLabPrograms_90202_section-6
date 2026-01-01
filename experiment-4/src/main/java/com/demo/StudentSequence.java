package com.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "student_sequence")
public class StudentSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_sequence_gen", allocationSize = 1)
    private Long id;

    private String name;

    public StudentSequence() {
    }

    public StudentSequence(String name) {
        this.name = name;
    }
}