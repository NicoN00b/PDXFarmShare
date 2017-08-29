package dao;

import models.*;

import java.util.List;

public interface OtherDao {

    //create
    void add (Other other);
    //read
    List<Other> getAll();

    Other findById(int id);
    //    //    //update
    void update(Other other, String newName, String newLocation, boolean pub, String newDescription, boolean barter);
    //    //    //delete
    void deleteById(int id);
//
//    void clearAllOthers();

}
