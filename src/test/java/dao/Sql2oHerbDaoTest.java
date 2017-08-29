package dao;

import models.Herb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oHerbDaoTest {

    private Connection conn;
    private Sql2oHerbDao herbDao;

    @Before
    public void setUp() throws Exception {
        String connectionString ="jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        herbDao = new Sql2oHerbDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingHerbSetsId() throws Exception {
        Herb testHerb = setupHerb();
        int originalHerbId = testHerb.getId();
        herbDao.add(testHerb);
        assertNotEquals(originalHerbId,testHerb.getId());
    }


    @Test
    public void addedHerbsAreReturnedFromGetAll() throws Exception {
        Herb testHerb = setupHerb();
        Herb herbTwo = setupHerb();
        herbDao.add(testHerb);
        herbDao.add(herbTwo);
        assertEquals(2, herbDao.getAll().size());
    }

    @Test
    public void herbCanBeFoundById() throws Exception {
        Herb herb = setupHerb();
        herbDao.add(herb);
        Herb foundHerb = herbDao.findById(herb.getId());
        assertEquals(herb, foundHerb);
    }

    @Test
    public void updateChangesHerbContent() throws Exception {
        Herb herb = setupHerb();
        herbDao.add(herb);
        herbDao.update(herb,"rainbow chard", "taylor st, montavilla", true, "I have Chard on the street for picking",true);
        Herb updatedHerb = herbDao.findById(herb.getId());
        assertNotEquals(herb, updatedHerb.getName());
    }

    @Test
    public void deleteByIdDeletesCorrectHerb() throws Exception {
        Herb testHerb = setupHerb();
        Herb herbTwo = setupHerb();
        herbDao.add(testHerb);
        herbDao.add(herbTwo);
        herbDao.deleteById(testHerb.getId());
        assertEquals(1, herbDao.getAll().size());
    }


    public Herb setupHerb(){
        return new Herb(123, "rosemary", "taylor st, montavilla", false, "I have rosemary on the street for picking", true, "Tuscan Blue", "four huge plants", true);
    }

}