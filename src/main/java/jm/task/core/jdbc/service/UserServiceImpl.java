package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.UserEntity;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
    private static final UserDaoHibernateImpl userHibernateDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userHibernateDao.createUsersTable();
    }

    public void dropUsersTable() {
        userHibernateDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userHibernateDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userHibernateDao.removeUserById(id);
    }

    public List<UserEntity> getAllUsers() {
        return userHibernateDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userHibernateDao.cleanUsersTable();
    }
}
