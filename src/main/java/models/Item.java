package models;

public class Item {
   public int id;
   public int userId;
   public String name;
   public String location;
   public boolean pub = false;
   public String description;
   public boolean barter = false;

//   public String type;

    public Item (int userId, String name, String location, boolean pub, String description, boolean barter){
        this.userId = userId;
        this.name = name;
        this.location = location;
        this.pub = pub;
        this.description = description;
        this.barter = barter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isPub() {
        return pub;
    }

    public void setPub(boolean pub) {
        this.pub = pub;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBarter() {
        return barter;
    }

    public void setBarter(boolean barter) {
        this.barter = barter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (userId != item.userId) return false;
        if (pub != item.pub) return false;
        if (barter != item.barter) return false;
        if (!name.equals(item.name)) return false;
        if (!location.equals(item.location)) return false;
        return description.equals(item.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + name.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + (pub ? 1 : 0);
        result = 31 * result + description.hashCode();
        result = 31 * result + (barter ? 1 : 0);
        return result;
    }
}

