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
    void update(int userId, String newName, String newLocation, boolean pub, String newDescription, boolean barter, String variety, String quantity, boolean youPick);
    //    //    //delete
    void deleteById(int id);
//
//    void clearAllFruits();

}