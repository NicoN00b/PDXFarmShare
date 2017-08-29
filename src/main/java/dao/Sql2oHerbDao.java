package dao;

import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oHerbDao implements HerbDao{

    private final Sql2o sql2o;

    public Sql2oHerbDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    public void add(Herb herb) {
        String sql = "INSERT INTO herbs (userId, name, location, pub, description, barter) VALUES (:userId, :name, :location, :pub, :description, :barter)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(herb)
                    .executeUpdate()
                    .getKey();
            herb.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Herb> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM herbs")
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Herb.class);
        }
    }

    @Override
    public Herb findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM herbs WHERE id = :id")
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Herb.class);
        }
    }

    @Override
    public void update(Herb herb, String newName, String newLocation, boolean pub, String newDescription, boolean barter){
        String sql = "UPDATE herbs SET (name, location, pub, description, barter) = (:name, :location, :pub, :description, :barter) WHERE id=:id";

        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("location", newLocation)
                    .addParameter("pub", pub)
                    .addParameter("description", newDescription)
                    .addParameter("barter", barter)
                    .addParameter("id", herb.getId())
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from herbs WHERE id = :id";
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

