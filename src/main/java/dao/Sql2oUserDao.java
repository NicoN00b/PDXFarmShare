package dao;

import models.Item;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oUserDao implements UserDao {

    private final Sql2o sql2o;

    public Sql2oUserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (userName, userAddress, userZip, userPhone, userEmail, userBio, userPass) VALUES (:userName, :userAddress, :userZip, :userPhone, :userEmail, :userBio, :userPass)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
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
    public List<Item> getAllItemsForAUser(int userId) {
        ArrayList<Item> items = new ArrayList<>();

        String joinQuery = "SELECT itemid FROM users_items WHERE userid = :userId";

        try (Connection con = sql2o.open()) {
            List<Integer> allItemsIds = con.createQuery(joinQuery)
                    .addParameter("userId", userId)
                    .executeAndFetch(Integer.class);
            for (Integer itemId : allItemsIds){
                String itemQuery = "SELECT * FROM items WHERE id = :itemId";
                items.add(
                        con.createQuery(itemQuery)
                                .addParameter("itemId", itemId)
                                .executeAndFetchFirst(Item.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return items;
    }


    @Override
    public List<User> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM users")
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public User findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM users WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public void update(User user, String newUserName, String newUserAddress, Integer newUserZip, String newUserPhone, String newUserEmail, String newUserBio, String newUserPass){
        String sql = "UPDATE users SET (username, useraddress, userzip, userphone, useremail, userbio, userpass) = (:username, :useraddress, :userzip, :userphone, :useremail, :userbio, :userpass) WHERE id=:id";

        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("username", newUserName)
                    .addParameter("useraddress", newUserAddress)
                    .addParameter("userzip", newUserZip)
                    .addParameter("userphone", newUserPhone)
                    .addParameter("useremail", newUserEmail)
                    .addParameter("userbio", newUserBio)
                    .addParameter("userpass", newUserPass)
                    .addParameter("id", user.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from users WHERE id = :id";
        String deleteJoin= "DELETE from users_items WHERE itemid = :itemid";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("itemid", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
