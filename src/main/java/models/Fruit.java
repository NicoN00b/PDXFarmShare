package models;

public class Fruit extends Item {
    private String variety;
    private String quantity;
    private boolean youPick = false;
//    public static final String DATABASE_TYPE = "fruit";

    public Fruit (int userId, String name, String location, boolean pub, String description, boolean barter, String variety, String quantity, boolean youPick) {
    super(userId, name, location, pub, description, barter);
    this.variety = variety;
    this.quantity = quantity;
    this.youPick = youPick;

//    type = DATABASE_TYPE;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean isYouPick() {
        return youPick;
    }

    public void setYouPick(boolean youPick) {
        this.youPick = youPick;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Fruit fruit = (Fruit) o;

        if (youPick != fruit.youPick) return false;
        if (!variety.equals(fruit.variety)) return false;
        return quantity.equals(fruit.quantity);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + variety.hashCode();
        result = 31 * result + quantity.hashCode();
        result = 31 * result + (youPick ? 1 : 0);
        return result;
    }
}
