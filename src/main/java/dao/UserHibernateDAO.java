package dao;


import org.hibernate.Session;
import org.hibernate.Transaction;

import util.DBHelper;
import model.User;

import java.util.List;
import java.util.logging.Logger;


public class UserHibernateDAO implements UserDAO {

    private static final Logger log = Logger.getLogger(UserHibernateDAO.class.getName());

    @Override
    public void insertUser(User user) {
        Session session = DBHelper.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    @Override
    public User selectUser(int id) {
        Session session = DBHelper.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        transaction.commit();
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        Session session = DBHelper.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("from User").list();
        transaction.commit();
        return users;
    }

    @Override
    public boolean updateUser(User user) {
        Session session = DBHelper.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        return false;
    }

    @Override
    public boolean deleteUserById(int id) {
        Session session = DBHelper.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        if (user!=null){
            session.delete(user);
            log.info("User is deleted");
        }
        transaction.commit();
        return false;
    }
}
