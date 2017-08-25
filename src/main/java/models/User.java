package models;

public class User {
    private String userName;
    private String userAddress;
    private Integer userZip;
    private Integer userPhone;
    private String userEmail;
    private String userBio;
    private String userPass;

    User (String userName, String userAddress, Integer userZip, Integer userPhone, String userEmail, String userBio, String userPass) {
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

    public Integer getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Integer userPhone) {
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
}
