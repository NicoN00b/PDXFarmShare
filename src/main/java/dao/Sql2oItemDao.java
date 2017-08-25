package dao;

import models.Item;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oItemDao implements ItemDao{

    private final Sql2o sql2o;

    public Sql2oItemDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Item item) {
        String sql = "INSERT INTO items (userId, name, location, pub, description, barter) VALUES (:userId, :name, :location, :pub, :description, :barter)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(item)
                    .executeUpdate()
                    .getKey();
            item.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Item> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM items")
                    .executeAndFetch(Item.class);
        }
    }

    @Override
    public Item findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM items WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Item.class);
        }
    }

    @Override
    public void update(Item item, String newName, String newLocation, boolean pub, String newDescription, boolean barter){
        String sql = "UPDATE items SET (name, location, pub, description, barter) = (:name, :location, :pub, :description, :barter) WHERE id=:id";

        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("location", newLocation)
                    .addParameter("pub", pub)
                    .addParameter("description", newDescription)
                    .addParameter("barter", barter)
                    .addParameter("id", item.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from items WHERE id = :id"; //raw sql
//        String deleteJoin = "DELETE from items_foodtypes WHERE restaurantid = :restaurantId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
//            con.createQuery(deleteJoin)
//                    .addParameter("restaurantId", id)
//                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

}
