package dao;

import models.Veggie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oVeggieDaoTest {

    private Connection conn;
    private Sql2oVeggieDao veggieDao;

    @Before
    public void setUp() throws Exception {
        String connectionString ="jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        veggieDao = new Sql2oVeggieDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingVeggieSetsId() throws Exception {
        Veggie testVeggie = setupVeggie();
        int originalVeggieId = testVeggie.getId();
        veggieDao.add(testVeggie);
        assertNotEquals(originalVeggieId,testVeggie.getId());
    }


    @Test
    public void addedVeggiesAreReturnedFromGetAll() throws Exception {
        Veggie testVeggie = setupVeggie();
        Veggie veggieTwo = setupVeggie();
        veggieDao.add(testVeggie);
        veggieDao.add(veggieTwo);
        assertEquals(2, veggieDao.getAll().size());
    }

    @Test
    public void veggieCanBeFoundById() throws Exception {
        Veggie veggie = setupVeggie();
        veggieDao.add(veggie);
        Veggie foundVeggie = veggieDao.findById(veggie.getId());
        assertEquals(veggie, foundVeggie);
    }

    @Test
    public void updateChangesVeggieContent() throws Exception {
        Veggie veggie = setupVeggie();
        veggieDao.add(veggie);
        veggieDao.update(veggie,"rainbow chard", "taylor st, montavilla", true, "I have Chard on the street for picking",true);
        Veggie updatedVeggie = veggieDao.findById(veggie.getId());
        assertNotEquals(veggie, updatedVeggie.getName());
    }

    @Test
    public void deleteByIdDeletesCorrectVeggie() throws Exception {
        Veggie testVeggie = setupVeggie();
        Veggie veggieTwo = setupVeggie();
        veggieDao.add(testVeggie);
        veggieDao.add(veggieTwo);
        veggieDao.deleteById(testVeggie.getId());
        assertEquals(1, veggieDao.getAll().size());
    }


    public Veggie setupVeggie(){
        return new Veggie(123, "kale", "taylor st, montavilla", false, "I have Kale on the street for picking", true, "italian", "four huge plants", true);
    }

}