package dao;

import models.Veggie;

import java.util.List;

public interface VeggieDao {

    //create
    void add (Veggie veggie);
    //read
    List<Veggie> getAll();

    Veggie findById(int id);
    //    //    //update
    void update(Veggie veggie, String newName, String newLocation, boolean pub, String newDescription, boolean barter);
    //    //    //delete
    void deleteById(int id);
//
//    void clearAllVeggies();

}
