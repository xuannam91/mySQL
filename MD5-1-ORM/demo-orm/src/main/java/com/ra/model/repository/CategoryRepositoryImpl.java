package com.ra.model.repository;

import com.ra.model.entity.Category;
import com.ra.model.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CategoryRepositoryImpl implements CategoryRepository{
//    tiêm SessionFactory
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            categories = session.createQuery("from Category").list();
            return categories;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean create(Category category) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            try {
                session.save(category);
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
    public Category find(Integer categoryID) {
        Category category = new Category();
        Session session = sessionFactory.openSession();
        try{
            category = session.get(Category.class, categoryID);
            return category;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean update(Category category) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            try {
                session.update(category);
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
    public void delete(Integer categoryID) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(find(categoryID));
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
}
