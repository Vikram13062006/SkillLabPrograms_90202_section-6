package com.example.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.entity.Product;
import com.example.util.HibernateUtil;

public class HQLDemo {

        public static void main(String[] args) {
                Transaction tx = null;

                // Use try-with-resources for session
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {

                        tx = session.beginTransaction();

                        // --- STEP 6.1: Insert Products ---
                        session.save(new Product("Laptop", "Electronics", 55000, 10));
                        session.save(new Product("Mouse", "Electronics", 500, 50));
                        session.save(new Product("Keyboard", "Electronics", 1200, 30));
                        session.save(new Product("Chair", "Furniture", 3500, 15));
                        session.save(new Product("Table", "Furniture", 8000, 5));
                        session.save(new Product("Pen", "Stationery", 20, 100));

                        tx.commit();

                        // --- STEP 6.2: Sorting by Price ASC ---
                        System.out.println("\n--- Price Ascending ---");
                        session.createQuery("FROM Product p ORDER BY p.price ASC", Product.class)
                                        .list()
                                        .forEach(System.out::println);

                        // --- STEP 6.3: Sorting by Quantity DESC ---
                        System.out.println("\n--- Quantity Descending ---");
                        session.createQuery("FROM Product p ORDER BY p.quantity DESC", Product.class)
                                        .list()
                                        .forEach(System.out::println);

                        // --- STEP 6.4 & 6.5: Pagination ---
                        Query<Product> query = session.createQuery("FROM Product", Product.class);

                        System.out.println("\n--- First 3 Products ---");
                        query.setFirstResult(0);
                        query.setMaxResults(3);
                        query.list().forEach(System.out::println);

                        System.out.println("\n--- Next 3 Products ---");
                        query.setFirstResult(3);
                        query.setMaxResults(3);
                        query.list().forEach(System.out::println);

                        // --- STEP 6.6: Count Products ---
                        Long count = session.createQuery("SELECT COUNT(p) FROM Product p", Long.class)
                                        .uniqueResult();
                        System.out.println("\nTotal Products: " + count);

                        // --- STEP 6.7: Count Quantity > 0 ---
                        Long countAvailable = session
                                        .createQuery("SELECT COUNT(p) FROM Product p WHERE p.quantity > 0", Long.class)
                                        .uniqueResult();
                        System.out.println("Available Products: " + countAvailable);

                        // --- STEP 6.8: Group By Description ---
                        System.out.println("\n--- Group By Description ---");
                        session.createQuery("SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description")
                                        .list()
                                        .forEach(obj -> {
                                                Object[] arr = (Object[]) obj;
                                                System.out.println(arr[0] + " -> " + arr[1]);
                                        });

                        // --- STEP 6.9: Min & Max Price ---
                        Object[] minMax = (Object[]) session
                                        .createQuery("SELECT MIN(p.price), MAX(p.price) FROM Product p")
                                        .uniqueResult();
                        System.out.println("\nMin Price: " + minMax[0]);
                        System.out.println("Max Price: " + minMax[1]);

                        // --- STEP 6.10: Filter by Price Range ---
                        System.out.println("\n--- Price Between 1000 and 10000 ---");
                        session.createQuery("FROM Product p WHERE p.price BETWEEN :min AND :max", Product.class)
                                        .setParameter("min", 1000.0)
                                        .setParameter("max", 10000.0)
                                        .list()
                                        .forEach(System.out::println);

                        // --- STEP 6.11: LIKE Example ---
                        System.out.println("\n--- Names Starting with L ---");
                        session.createQuery("FROM Product p WHERE p.name LIKE 'L%'", Product.class)
                                        .list()
                                        .forEach(System.out::println);

                } catch (Exception e) {
                        if (tx != null)
                                tx.rollback(); // rollback if exception occurs
                        e.printStackTrace();
                } finally {
                        HibernateUtil.shutdown(); // close SessionFactory properly
                }
        }
}
