package dao;

import models.Item;
import models.User;

import java.util.List;

public interface UserDao {

    //create
    void add (User user);

    //read
    List<User> getAll();
//    List<Item> getAllItemsForAUser(int userId); //D - we will implement this soon.
//
    User findById(int id);
//
//    //update
    void update(User user, String newUserName, String newUserAddress, Integer newUserZip, String newUserPhone, String newUserEmail, String newUserBio, String newUserPass);
//
//    //delete
    void deleteById(int id);
}

