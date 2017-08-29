package dao;

import models.Fruit;
import models.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oFruitDaoTest {

    private Connection conn;
    private Sql2oFruitDao fruitDao;

    @Before
    public void setUp() throws Exception {
        String connectionString ="jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        fruitDao = new Sql2oFruitDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingFruitSetsId() throws Exception {
        Fruit testFruit = setupFruit();
        int originalFruitId = testFruit.getId();
        fruitDao.add(testFruit);
        assertNotEquals(originalFruitId,testFruit.getId());
    }


    @Test
    public void addedFruitsAreReturnedFromGetAll() throws Exception {
        Fruit testFruit = setupFruit();
        Fruit fruitTwo = setupFruit();
        fruitDao.add(testFruit);
        fruitDao.add(fruitTwo);
        assertEquals(2, fruitDao.getAll().size());
    }

    @Test
    public void fruitCanBeFoundById() throws Exception {
        Fruit fruit = setupFruit();
        fruitDao.add(fruit);
        Fruit foundFruit = fruitDao.findById(fruit.getId());
        assertEquals(fruit, foundFruit);
    }

    @Test
    public void updateChangesFruitContent() throws Exception {
        Fruit fruit = setupFruit();
        fruitDao.add(fruit);
        fruitDao.update(fruit,"chesterberries", "taylor st, montavilla", true, "new Chesterberry hybrids",true);
        Fruit updatedFruit = fruitDao.findById(fruit.getId());
        assertNotEquals(fruit, updatedFruit.getName());
    }

    @Test
    public void deleteByIdDeletesCorrectFruit() throws Exception {
        Fruit testFruit = setupFruit();
        Fruit fruitTwo = setupFruit();
        fruitDao.add(testFruit);
        fruitDao.add(fruitTwo);
        fruitDao.deleteById(testFruit.getId());
        assertEquals(1, fruitDao.getAll().size());
    }


    public Fruit setupFruit(){
        return new Fruit(1, "blackberry", "78th between hawthorne and madison", true, "alley is full of backberries", false, "himalayan", "as much as you like",  true);
    }

}