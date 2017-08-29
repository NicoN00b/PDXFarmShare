package dao;

import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oVeggieDao implements VeggieDao{

    private final Sql2o sql2o;

    public Sql2oVeggieDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    public void add(Veggie veggie) {
        String sql = "INSERT INTO veggies (userId, name, location, pub, description, barter) VALUES (:userId, :name, :location, :pub, :description, :barter)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(veggie)
                    .executeUpdate()
                    .getKey();
            veggie.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Veggie> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM veggies")
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Veggie.class);
        }
    }

    @Override
    public Veggie findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM veggies WHERE id = :id")
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Veggie.class);
        }
    }

    @Override
    public void update(Veggie veggie, String newName, String newLocation, boolean pub, String newDescription, boolean barter){
        String sql = "UPDATE veggies SET (name, location, pub, description, barter) = (:name, :location, :pub, :description, :barter) WHERE id=:id";

        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("location", newLocation)
                    .addParameter("pub", pub)
                    .addParameter("description", newDescription)
                    .addParameter("barter", barter)
                    .addParameter("id", veggie.getId())
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from veggies WHERE id = :id";
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

