import com.google.gson.Gson;
import dao.Sql2oItemDao;
import exceptions.ApiException;
import models.Item;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.Spark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.after;
import static spark.Request.*;

public class App {

    public static void main(String[] args) {
        Sql2oItemDao itemDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/farm-share.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        itemDao = new Sql2oItemDao(sql2o);
        conn = sql2o.open();

        post("/items/new", "application/json", (req, res) -> {
            Item item = gson.fromJson(req.body(), Item.class);
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

        get("/items/:id", "application/json", (req, res) -> {
            res.type("application/json");
            int itemId = Integer.parseInt(req.params("id"));
            res.type("application/json");

//            I want to make this a valid exception message
//            if (itemId == null){
//                throw new ApiException(404, String.format("It appears this item is no longer available"));
//            }
            return gson.toJson(itemDao.findById(itemId));
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
//        after((res, req) -> {
//            res.type("application/json");
//        });
    }
}
