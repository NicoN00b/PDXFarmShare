package dao;

import models.Fruit;
import models.Fruit;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oFruitDao implements FruitDao{
    private final Sql2o sql2o;

    public Sql2oFruitDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    public void add(Fruit fruit) {
        String sql = "INSERT INTO fruits (userId, name, location, pub, description, barter, variety, quantity, youpick) VALUES (:userId, :name, :location, :pub, :description, :barter, :variety, :quantity, :youpick)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(fruit)
                    .executeUpdate()
                    .getKey();
            fruit.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Fruit> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM fruits")
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Fruit.class);
        }
    }

    @Override
    public Fruit findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM fruits WHERE id = :id")
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Fruit.class);
        }
    }

    @Override
    public void update (int userId, String newName, String newLocation, boolean newPub, String newDescription, boolean newBarter, String newVariety, String newQuantity, boolean newYouPick) {
        String sql = "UPDATE fruits SET (userid, name, location, pub, description, barter, variety, quantity, youpick) = (:userid, :name, :location, :pub, :description, :barter, :variety, :quantity, :youpick) WHERE id=:id";

        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("userid", userId)
                    .addParameter("name", newName)
                    .addParameter("location", newLocation)
                    .addParameter("pub", newPub)
                    .addParameter("description", newDescription)
                    .addParameter("barter", newBarter)
                    .addParameter("variety", newVariety)
                    .addParameter("quantity", newQuantity)
                    .addParameter("youpick", newYouPick)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from fruits WHERE id = :id";
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
