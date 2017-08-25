package dao;

import models.*;
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
        String sql = "INSERT INTO items (userId, name, location, pub, description, barter, type) VALUES (:userId, :name, :location, :pub, :description, :barter, :type)";
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
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Item.class);
        }
    }

    @Override
    public List<Fruit> getAllFruit() {
        String sql = "SELECT * FROM monsters WHERE type='fruit';";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Fruit.class);
        }
    }

    @Override
    public List<Veggie> getAllVeggie() {
        String sql = "SELECT * FROM monsters WHERE type='veggie';";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Veggie.class);
        }
    }

    @Override
    public List<Herb> getAllHerb() {
        String sql = "SELECT * FROM monsters WHERE type='herb';";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Herb.class);
        }
    }

    @Override
    public List<Other> getAllOther() {
        String sql = "SELECT * FROM monsters WHERE type='other';";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Other.class);
        }
    }

    @Override
    public Item findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM items WHERE id = :id")
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
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
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from items WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

}
