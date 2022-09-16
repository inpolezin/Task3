package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.UserEntity;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    Transaction transaction;


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users (id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "age SMALLINT, " +
                "PRIMARY KEY (id) )";

        try (Session session = Util.getSession()){

            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";

        try (Session session = Util.getSession()){

            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession()){

            transaction = session.beginTransaction();
            session.save(new UserEntity(name, lastName, age));
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()){

            UserEntity user = session.get(UserEntity.class, id);
            transaction = session.beginTransaction();
            if(user != null) {
                session.delete(user);
            }
            transaction.commit();
        }
    }

    @Override
    public List<UserEntity> getAllUsers() {
        String hql = "FROM UserEntity";

        try (Session session = Util.getSession()){

            return session.createQuery(hql).getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {

        String sql = "TRUNCATE TABLE Users";

        try (Session session = Util.getSession()){

            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }

    }
}
