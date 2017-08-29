import com.google.gson.Gson;
import dao.Sql2oItemDao;
import dao.Sql2oUserDao;
import exceptions.ApiException;
import models.Item;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.Spark;

import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.after;
import static spark.Request.*;

public class App {

    public static void main(String[] args) {
        Sql2oItemDao itemDao;
        Sql2oUserDao userDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/farm-share.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        itemDao = new Sql2oItemDao(sql2o);
        conn = sql2o.open();

        post("/users/new", "application/json", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            userDao.add(user);
            res.status(201);;
            res.type("application/json");
            return gson.toJson(user);
        });

        post("users/:userid/items/new", "application/json", (req, res) -> {
            //addItemToUserProfile
            int userid = Integer.parseInt(req.params("userId"));
            Item item = gson.fromJson(req.body(), Item.class);
            item.setUserId(userid);
            itemDao.add(item);
            res.status(201);;
            res.type("application/json");
            return gson.toJson(item);
        });

        get("/items", "application/json", (req, res) -> {
            res.type("application/json");
            List<Item> itemList = itemDao.getAll();

            if (itemList == null){
                throw new ApiException(404, String.format("It appears no items are available in your area"));
            }

            return gson.toJson(itemDao.getAll());
        });

        get("/users", "application/json", (req, res) -> {
            res.type("application/json");
            List<User> userList = userDao.getAll();

            if (userList == null){
                throw new ApiException(404, String.format("It appears no users in your area"));
            }

            return gson.toJson(userDao.getAll());
        });

        get("/users/:userid/items", "application/json", (req, res) -> {
            res.type("application/json");
            int userId = Integer.parseInt(req.params("userid"));
            List<Item> allItems = userDao.getAllItemsForAUser(1);
            System.out.println(allItems.size());
            res.type("application/json");
            return gson.toJson(allItems);
        });

        get("/users/:userid/items/:id", "application/json", (req, res) -> {
            res.type("application/json");
            int userId = Integer.parseInt(req.params("userId"));
            int itemId = Integer.parseInt(req.params("id"));
            res.type("application/json");
//            I want to make this a valid exception message
//            if (itemId == null){
//                throw new ApiException(404, String.format("It appears this item is no longer available"));
//            }
            return gson.toJson(itemDao.findById(itemId));
        });

        get("/users/:id", "application/json", (req, res) -> {
            res.type("application/json");
            int userId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(userDao.findById(userId));
        });



        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = (ApiException) exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });


//        Spark wouldn't recognize this filter
//        after((res, req) ->{
//            res.type("application/json");
//        });
    }
}
