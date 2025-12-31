package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

    private static final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    public static void main(String[] args) {

        try {
            createStudent();
            readStudent();
            updateStudent();
            readStudent();
            deleteStudent();
            readStudent();
        } finally {
            factory.close();
        }
    }

    
    public static void createStudent() {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();

            Student s = new Student(11, "Sai Prasad", 95);
            session.persist(s);

            session.getTransaction().commit();
            System.out.println("Student Created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
    public static void readStudent() {
        try (Session session = factory.openSession()) {
            Student s = session.get(Student.class, 11);
            if (s != null) {
                System.out.println("Student Found: " + s.getId() + " " + s.getName() + " " + s.getMarks());
            } else {
                System.out.println("Student Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent() {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();

            Student s = session.get(Student.class, 11);
            if (s != null) {
                s.setMarks(100);
                s.setName("Sai Prasad Updated");
                session.merge(s);
                System.out.println("Student Updated");
            } else {
                System.out.println("Student Not Found for Update");
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public static void deleteStudent() {
        try (Session session = factory.openSession()) {
            session.getTransaction().begin();

            Student s = session.get(Student.class, 11);
            if (s != null) {
                session.remove(s);
                System.out.println("Student Deleted");
            } else {
                System.out.println("Student Not Found for Delete");
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
