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
        try {
            session = DBHelper.getSessionFactory().openSession();
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
        User user = null;
        try {
            session = sessionFactory.openSession();
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
        User user = null;
        try {
            session = sessionFactory.openSession();
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
        User userLogin = null;
        try {
            session = sessionFactory.openSession();
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
        List<User> users = null;
        try {
            session = sessionFactory.openSession();
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
        try {
            session = sessionFactory.openSession();
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

