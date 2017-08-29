package dao;

import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oOtherDao implements OtherDao{

    private final Sql2o sql2o;

    public Sql2oOtherDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    public void add(Other other) {
        String sql = "INSERT INTO others (userId, name, location, pub, description, barter) VALUES (:userId, :name, :location, :pub, :description, :barter)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(other)
                    .executeUpdate()
                    .getKey();
            other.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Other> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM others")
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Other.class);
        }
    }

    @Override
    public Other findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM others WHERE id = :id")
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Other.class);
        }
    }

    @Override
    public void update(Other other, String newName, String newLocation, boolean pub, String newDescription, boolean barter){
        String sql = "UPDATE others SET (name, location, pub, description, barter) = (:name, :location, :pub, :description, :barter) WHERE id=:id";

        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("location", newLocation)
                    .addParameter("pub", pub)
                    .addParameter("description", newDescription)
                    .addParameter("barter", barter)
                    .addParameter("id", other.getId())
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from others WHERE id = :id";
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
