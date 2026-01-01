package com.example;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Session session = HibernateUtil.getSessionFactory().openSession();

        boolean running = true;
        while (running) {
            System.out.println("\n=== Student Management ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: // Add Student
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    int marks = sc.nextInt();

                    Transaction tx1 = session.beginTransaction();
                    Student student = new Student();
                    student.setId(id);
                    student.setName(name);
                    student.setMarks(marks);
                    session.save(student);
                    tx1.commit();
                    System.out.println("Student Added Successfully!");
                    break;

                case 2: // View Student
                    System.out.print("Enter ID to view: ");
                    int viewId = sc.nextInt();
                    Student s = session.get(Student.class, viewId);
                    if (s != null) {
                        System.out.println("Student Found: " + s.getId() + " " + s.getName() + " " + s.getMarks());
                    } else {
                        System.out.println("Student Not Found!");
                    }
                    break;

                case 3: // Update Student
                    System.out.print("Enter ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    Student upStudent = session.get(Student.class, updateId);
                    if (upStudent != null) {
                        System.out.print("Enter new Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter new Marks: ");
                        int newMarks = sc.nextInt();

                        Transaction tx2 = session.beginTransaction();
                        upStudent.setName(newName);
                        upStudent.setMarks(newMarks);
                        session.update(upStudent);
                        tx2.commit();
                        System.out.println("Student Updated!");
                    } else {
                        System.out.println("Student Not Found!");
                    }
                    break;

                case 4: // Delete Student
                    System.out.print("Enter ID to delete: ");
                    int deleteId = sc.nextInt();
                    Student delStudent = session.get(Student.class, deleteId);
                    if (delStudent != null) {
                        Transaction tx3 = session.beginTransaction();
                        session.delete(delStudent);
                        tx3.commit();
                        System.out.println("Student Deleted!");
                    } else {
                        System.out.println("Student Not Found!");
                    }
                    break;

                case 5: // Exit
                    running = false;
                    break;

                default:
                    System.out.println("Invalid Option! Try again.");
            }
        }

        session.close();
        sc.close();
        System.out.println("Application Closed!");
    }
}
