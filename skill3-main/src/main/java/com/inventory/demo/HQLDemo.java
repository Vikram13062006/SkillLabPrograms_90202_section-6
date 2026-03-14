package com.inventory.demo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.inventory.entity.Product;
import com.inventory.loader.ProductDataLoader;
import com.inventory.util.HibernateUtil;

public class HQLDemo {

 public static void main(String[] args) {

  SessionFactory factory = HibernateUtil.getSessionFactory();
  Session session = factory.openSession();

  ProductDataLoader.loadSampleProducts(session);

  sortByPriceAsc(session);
  countProducts(session);
  findMinMax(session);

  session.close();
  factory.close();
 }

 public static void sortByPriceAsc(Session session) {
  Query<Product> query = session.createQuery("FROM Product p ORDER BY p.price ASC", Product.class);
  List<Product> list = query.list();

  System.out.println("\nSorted by Price ASC:");
  list.forEach(System.out::println);
 }

 public static void countProducts(Session session) {
  Query<Long> query = session.createQuery("SELECT COUNT(p) FROM Product p", Long.class);
  Long count = query.uniqueResult();
  System.out.println("\nTotal Products: " + count);
 }

 public static void findMinMax(Session session) {
  Query<Object[]> query = session.createQuery("SELECT MIN(p.price), MAX(p.price) FROM Product p", Object[].class);
  Object[] result = query.uniqueResult();

  System.out.println("\nMin Price: " + result[0]);
  System.out.println("Max Price: " + result[1]);
 }
}