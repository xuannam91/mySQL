package com.ra.model.repository;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductRepositoryImpl implements ProductRepository{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            productList = session.createQuery("from Product p ORDER BY p.productId DESC").list();
            return productList;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean create(Product product) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(product);
                transaction.commit();
                return true;
            }catch (Exception e){
                e.printStackTrace();
                transaction.rollback();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Product find(Integer productID) {
        Product product = new Product();
        Session session = sessionFactory.openSession();
        try {
            product = session.get(Product.class, productID);
            return product;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }


    @Override
    public Boolean update(Product product) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            try {
                session.update(product);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
                transaction.rollback();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void delete(Integer productID) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(find(productID));
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
}
