package dao;

import models.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oItemDaoTest {

    private Connection conn;
    private Sql2oItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        String connectionString ="jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        itemDao = new Sql2oItemDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingItemSetsId() throws Exception {
        Item testItem = setupItem();
        int originalItemId = testItem.getId();
        itemDao.add(testItem);
        assertNotEquals(originalItemId,testItem.getId());
    }


    @Test
    public void addedItemsAreReturnedFromGetAll() throws Exception {
        Item testItem = setupItem();
        Item itemTwo = setupItem();
        itemDao.add(testItem);
        itemDao.add(itemTwo);
        assertEquals(2, itemDao.getAll().size());
    }

    @Test
    public void itemCanBeFoundById() throws Exception {
        Item item = setupItem();
        itemDao.add(item);
        Item foundItem = itemDao.findById(item.getId());
        assertEquals(item, foundItem);
    }

    @Test
    public void updateChangesItemContent() throws Exception {
        Item item = setupItem();
        itemDao.add(item);
        itemDao.update(item,"rainbow chard", "taylor st, montavilla", true, "I have Chard on the street for picking",true);
        Item updatedItem = itemDao.findById(item.getId());
        assertNotEquals(item, updatedItem.getName());
    }

    @Test
    public void deleteByIdDeletesCorrectItem() throws Exception {
        Item testItem = setupItem();
        Item itemTwo = setupItem();
        itemDao.add(testItem);
        itemDao.add(itemTwo);
        itemDao.deleteById(testItem.getId());
        assertEquals(1, itemDao.getAll().size());
    }


    public Item setupItem(){
        return new Item(123, "kale", "taylor st, montavilla", false, "I have Kale on the street for picking", false);
    }

}