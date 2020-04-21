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
            session.save(user);
        } catch (HibernateException e) {
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
        } catch (HibernateException e) {
            logger.info("HibernateException in class UserHibernateDAO in method getUserById()");
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public User getUserByName(String name) {
        session = sessionFactory.openSession();
        User user = null;
        try {
            user = (User) session.get(User.class, name);
        } catch (HibernateException e) {
            logger.info("HibernateException in class UserHibernateDAO in method getUserByName()");
        } finally {
            session.close();
        }
        return user;
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
        } catch (HibernateException e) {
            transaction.rollback();
            logger.info("HibernateException in class UserHibernateDAO in method getUserByNameAndPassword()");
        } finally {
            session.close();
        }
        return userLogin;
    }

    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.openSession();
        List<User> users = null;
        try {
            users = session.createQuery("from User").list();
        } catch (HibernateException e) {
            logger.info("HibernateException in class UserHibernateDAO in method getAllUsers()");
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public boolean updateUser(User user) {
        session = sessionFactory.openSession();
        try {
            session.update(user);
        } catch (HibernateException e) {
            logger.info("HibernateException in class UserHibernateDAO in method updateUser()");
        } finally {
            session.close();
        }
        return false;
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
        } catch (HibernateException e) {
            transaction.rollback();
            logger.info("HibernateException in class UserHibernateDAO in method deleteUserById()");
        } finally {
            session.close();
        }
        return false;
    }
}

