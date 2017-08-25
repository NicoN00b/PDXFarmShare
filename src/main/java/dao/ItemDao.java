package dao;

import models.*;

import java.util.List;

public interface ItemDao {

    //create
    void add (Item item);
    //read
    List<Item> getAll();
    List<Fruit> getAllFruit();
    List<Veggie> getAllVeggie();
    List<Herb> getAllHerb();
    List<Other> getAllOther();
//
    Item findById(int id);
//    //    //update
    void update(Item item, String newName, String newLocation, boolean pub, String newDescription, boolean barter);
//    //    //delete
    void deleteById(int id);
//
//    void clearAllItems();

}
