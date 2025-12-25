package com.example;

import com.example.dao.StudentDAO;
import com.example.entity.Student;

public class MainApp {
    public static void main(String[] args) {

        Student student1 = new Student("Karthik", 100);
         Student student2= new Student("Vikram", 100);
        StudentDAO dao = new StudentDAO();
        dao.saveStudent(student1);
        dao.saveStudent(student2);
        System.out.println("Student saved successfully!");
    }
}
