package dao;

import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {

    private Connection conn;
    private Sql2oUserDao userDao;

    @Before
    public void setUp() throws Exception {
        String connectionString ="jdbc:h2:mem:newing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingUserSetsId() throws Exception {
        User user = setupUser();
        int userId = user.getId();
        userDao.add(user);
        assertNotEquals(userId, user.getId());
    }

    @Test
    public void userCanBeFoundById() throws Exception {
        User user = setupUser();
        userDao.add(user);
        User foundUser = userDao.findById(user.getId());
        assertEquals(user, foundUser);
    }


    @Test
    public void addedUsersAreReturnedFromGetAll() throws Exception {
        User userOne = setupUser();
        User userTwo = setupUser();
        userDao.add(userOne);
        userDao.add(userTwo);
        assertEquals(2, userDao.getAll().size());
    }

    @Test
    public void updateChangesUserContent() throws Exception {
        User user = setupUser();
        userDao.add(user);
        userDao.update(user,"jamesGrownup", "1400 NW Irving St", 97209, "5035551234","jamesAnAdult@gmail.com", "Grown Up microfarmer", "Gr0wnA$$Pers0n");
        User updatedUser = userDao.findById(user.getId());
        assertNotEquals(user, updatedUser.getUserName());
    }

    @Test
    public void deleteByIdDeletesCorrectUser() throws Exception {
        User userOne = setupUser();
        User userTwo = setupUser();
        userDao.add(userOne);
        userDao.add(userTwo);
        userDao.deleteById(userOne.getId());
        assertEquals(1, userDao.getAll().size());
    }



    public User setupUser(){
        return new User("james007", "1400 NW Irving St", 97209, "5035551234", "james007@aol.com", "Anglophile and microfarmer", "mi5007");

    }


}