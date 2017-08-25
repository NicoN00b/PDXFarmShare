package models;

public class User {
    private String userName;
    private String userAddress;
    private Integer userZip;
    private String userPhone;
    private String userEmail;
    private String userBio;
    private String userPass;
    private int id;

    public User (String userName, String userAddress, Integer userZip, String userPhone, String userEmail, String userBio, String userPass) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.userZip = userZip;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userBio = userBio;
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getUserZip() {
        return userZip;
    }

    public void setUserZip(Integer userZip) {
        this.userZip = userZip;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!userName.equals(user.userName)) return false;
        if (!userAddress.equals(user.userAddress)) return false;
        if (!userZip.equals(user.userZip)) return false;
        if (!userPhone.equals(user.userPhone)) return false;
        if (!userEmail.equals(user.userEmail)) return false;
        if (!userBio.equals(user.userBio)) return false;
        return userPass.equals(user.userPass);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + userAddress.hashCode();
        result = 31 * result + userZip.hashCode();
        result = 31 * result + userPhone.hashCode();
        result = 31 * result + userEmail.hashCode();
        result = 31 * result + userBio.hashCode();
        result = 31 * result + userPass.hashCode();
        result = 31 * result + id;
        return result;
    }
}
