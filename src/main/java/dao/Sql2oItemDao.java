package dao;

import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oItemDao implements ItemDao{

    private final Sql2o sql2o;

    public Sql2oItemDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


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
    public void addItemToUserProfile(Item item, User user){
        String sql = "INSERT INTO users_items (userid, itemid, name) VALUES (:userId, :itemId, :name)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("userId", user.getId())
                    .addParameter("itemId", item.getId())
                    .addParameter("name", item.getName())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<User> getAllUsersByItemName(String name) {

        ArrayList<User> users = new ArrayList<>();

        String joinQuery = "SELECT userid FROM users_items WHERE itemid = :itemId";

        try (Connection con = sql2o.open()) {
            List<Integer> allUserIds = con.createQuery(joinQuery)
                    .addParameter("name", name)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer userId : allUserIds){
                String userQuery = "SELECT * FROM users WHERE id = :userId";
                users.add(
                        con.createQuery(userQuery)
                                .addParameter("userId", userId)
                                .executeAndFetchFirst(User.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return users;
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
        String deleteJoin= "DELETE from users_items WHERE userid = :userid";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("userid", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

}
