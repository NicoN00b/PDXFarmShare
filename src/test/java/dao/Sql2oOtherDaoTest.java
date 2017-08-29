package dao;

import models.Other;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oOtherDaoTest {

    private Connection conn;
    private Sql2oOtherDao otherDao;

    @Before
    public void setUp() throws Exception {
        String connectionString ="jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        otherDao = new Sql2oOtherDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingOtherSetsId() throws Exception {
        Other testOther = setupOther();
        int originalOtherId = testOther.getId();
        otherDao.add(testOther);
        assertNotEquals(originalOtherId,testOther.getId());
    }


    @Test
    public void addedOthersAreReturnedFromGetAll() throws Exception {
        Other testOther = setupOther();
        Other otherTwo = setupOther();
        otherDao.add(testOther);
        otherDao.add(otherTwo);
        assertEquals(2, otherDao.getAll().size());
    }

    @Test
    public void otherCanBeFoundById() throws Exception {
        Other other = setupOther();
        otherDao.add(other);
        Other foundOther = otherDao.findById(other.getId());
        assertEquals(other, foundOther);
    }

    @Test
    public void updateChangesOtherContent() throws Exception {
        Other other = setupOther();
        otherDao.add(other);
        otherDao.update(other,"rainbow chard", "taylor st, montavilla", true, "I have Chard on the street for picking",true);
        Other updatedOther = otherDao.findById(other.getId());
        assertNotEquals(other, updatedOther.getName());
    }

    @Test
    public void deleteByIdDeletesCorrectOther() throws Exception {
        Other testOther = setupOther();
        Other otherTwo = setupOther();
        otherDao.add(testOther);
        otherDao.add(otherTwo);
        otherDao.deleteById(testOther.getId());
        assertEquals(1, otherDao.getAll().size());
    }


    public Other setupOther(){
        return new Other(123, "tools", "taylor st, montavilla", false, "some old but durable tools to good home", false, "12 well-rounded garden tools");
    }

}