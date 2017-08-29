package models;

public class Other extends Item {
    private String quantity;
//    public static final String DATABASE_TYPE = "other";

    public Other (int userId, String name, String location, boolean pub, String description, boolean barter, String quantity) {
        super(userId, name, location, pub, description, barter);
        this.quantity = quantity;

//        type = DATABASE_TYPE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
