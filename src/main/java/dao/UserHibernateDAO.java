package dao;


import model.User;
import org.hibernate.*;
import util.DBHelper;

import java.util.List;
import java.util.logging.Logger;


public class UserHibernateDAO implements UserDAO {

    private SessionFactory sessionFactory = DBHelper.getSessionFactory();
    private Session session;
    private Transaction transaction = null;

    Logger logger = Logger.getLogger(UserHibernateDAO.class.getName());

    @Override
    public void createUser(User user) {
        session = DBHelper.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.info("HibernateException in class UserHibernateDAO in method createUser()");
        } finally {
            session.close();
        }
    }

    @Override
    public User getUserById(Integer id) {
        session = sessionFactory.openSession();
        User user = null;
        try {
            user = (User) session.get(User.class, id);
            return user;
        } catch (HibernateException e) {
            logger.info("HibernateException in class UserHibernateDAO in method getUserById()");
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public User getUserByName(String name) {
        session = sessionFactory.openSession();
        User user = null;
        try {
            user = (User) session.get(User.class, name);
            return user;
        } catch (HibernateException e) {
            logger.info("HibernateException in class UserHibernateDAO in method getUserByName()");
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        session = sessionFactory.openSession();
        User userLogin = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User where name = :paramName");
            query.setParameter("paramName", name);
            List<User> users = query.list();
            for (User user : users) {
                if (user.getName().equals(name) && user.getPassword().equals(password)) {
                    userLogin = user;
                }
            }
            transaction.commit();
            return userLogin;
        } catch (HibernateException e) {
            transaction.rollback();
            logger.info("HibernateException in class UserHibernateDAO in method getUserByNameAndPassword()");
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.openSession();
        List<User> users = null;
        try {
            users = session.createQuery("from User").list();
            return users;
        } catch (HibernateException e) {
            logger.info("HibernateException in class UserHibernateDAO in method getAllUsers()");
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateUser(User user) {
        session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return false;
        } catch (HibernateException e) {
            transaction.rollback();
            logger.info("HibernateException in class UserHibernateDAO in method updateUser()");
            return true;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deleteUserById(Integer id) {
        session = sessionFactory.openSession();
        User user;
        try {
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
            return false;
        } catch (HibernateException e) {
            transaction.rollback();
            logger.info("HibernateException in class UserHibernateDAO in method deleteUserById()");
            return true;
        } finally {
            session.close();
        }
    }
}

