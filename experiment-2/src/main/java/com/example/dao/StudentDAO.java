package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.entity.Student;
import com.example.util.HibernateUtil;

public class StudentDAO {

    public void saveStudent(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(student);
        tx.commit();
        session.close();
    }

    public List<Student> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Student";
        List<Student> list = session.createQuery(hql, Student.class).list();
        session.close();
        return list;
    }
}