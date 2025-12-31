package com.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(StudentAuto.class)
                .addAnnotatedClass(StudentIdentity.class)
                .addAnnotatedClass(StudentSequence.class)
                .buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();

        session.save(new StudentAuto("Raja (AUTO)"));
        session.save(new StudentIdentity("Mano (IDENTITY)"));
        // session.save(new StudentSequence("Kumar (SEQUENCE)"));

        session.getTransaction().commit();
        session.close();
        sf.close();

        System.out.println("Data inserted successfully!");
    }
}