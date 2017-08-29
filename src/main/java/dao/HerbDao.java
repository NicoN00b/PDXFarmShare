package dao;

import models.Herb;

import java.util.List;

public interface HerbDao {

    //create
    void add (Herb herb);
    //read
    List<Herb> getAll();

    Herb findById(int id);
    //    //    //update
    void update(Herb herb, String newName, String newLocation, boolean pub, String newDescription, boolean barter);
    //    //    //delete
    void deleteById(int id);
//
//    void clearAllHerbs();

}
