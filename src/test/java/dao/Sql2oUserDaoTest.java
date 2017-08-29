package dao;

import models.Item;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {

    private Connection conn;
    private Sql2oUserDao userDao;
    private Sql2oItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        String connectionString ="jdbc:h2:mem:newing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        itemDao = new Sql2oItemDao(sql2o);
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
    public void getAllItemsForAUserReturnsItemsCorrectly() throws Exception {
        Item testItem  = new Item(123, "kale", "taylor st, montavilla", false, "I have Kale on the street for picking", false);
        itemDao.add(testItem);

        Item otherItem  = new Item(1, "blackberry", "78th between hawthorne and madison", true, "alley is full of backberries", false);
        itemDao.add(otherItem);

        User testUser = setupUser();
        userDao.add(testUser);
        userDao.addItemToUserProfile(testItem,testUser);
        userDao.addItemToUserProfile(otherItem, testUser);
        System.out.println(userDao.getAllItemsForAUser(testUser.getId()));

        Item[] items = {testItem, otherItem}; //oh hi what is this?

        assertEquals(userDao.getAllItemsForAUser(testUser.getId()), Arrays.asList(items));
    }

    @Test
    public void deletingUserAlsoUpdatesJoinTable() throws Exception {
        Item testItem  = new Item(1, "blackberry", "78th between hawthorne and madison", true, "alley is full of backberries", false);
        itemDao.add(testItem);

        User testUser = setupUser();
        userDao.add(testUser);

        User altUser = setupAltUser();
        userDao.add(altUser);

        userDao.addItemToUserProfile(testItem, testUser);
        userDao.addItemToUserProfile(testItem, altUser);

        userDao.deleteById(testUser.getId());
        assertEquals(0, userDao.getAllItemsForAUser(testUser.getId()).size());
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
    public User setupAltUser() {
        return new User("james007", "1400 NW Irving St", 97209, "5035551234", "james007@aol.com", "Anglophile and microfarmer", "mi5007");
    }

}