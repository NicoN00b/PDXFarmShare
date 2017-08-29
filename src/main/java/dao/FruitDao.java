package dao;

import models.*;

import java.util.List;

public interface FruitDao {

    //create
    void add (Fruit fruit);
    //read
    List<Fruit> getAll();

    Fruit findById(int id);
    //    //    //update
    void update(Fruit fruit, String newName, String newLocation, boolean pub, String newDescription, boolean barter);
    //    //    //delete
    void deleteById(int id);
//
//    void clearAllFruits();

}