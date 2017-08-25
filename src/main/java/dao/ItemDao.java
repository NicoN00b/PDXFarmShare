package dao;

import models.*;

import java.util.List;

public interface ItemDao {

    //create
    void add (Item item);
    //read
    List<Item> getAll();
//    List<Fruit> getAllFruitByItem(int itemId);
//    List<Veggie> getAllVeggieByItem(int itemId);
//    List<Herb> getAllHerbByItem(int itemId);
//    List<Other> getAllOtherByItem(int itemId);
//
    Item findById(int id);
//    //    //update
    void update(Item item, String newName, String newLocation, boolean pub, String newDescription, boolean barter);
//    //    //delete
    void deleteById(int id);
//
//    void clearAllItems();

}
