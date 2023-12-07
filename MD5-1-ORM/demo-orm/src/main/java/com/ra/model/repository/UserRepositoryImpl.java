package com.ra.model.repository;

import com.ra.model.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    // tiem SessionFactory
    private SessionFactory sessionFactory;
    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        // khởi tạo đối tượng sesssion
        Session session = sessionFactory.openSession();
        try {
            list = session.createQuery("from User").list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }

    @Override
    public Boolean save(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(user);
                transaction.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Boolean update(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(user);
                transaction.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public User find(Integer id){
        Session session = sessionFactory.openSession();
        try{
            User user = session.get(User.class, id);
            return user;
        }catch (Exception e){

            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }



    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(find(id));
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
}
