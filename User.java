package Model;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
    private int groupNumber;

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public User() {
    }

    public User(String userName, String email, String password, int groupNumber) {
        this.userName = userName;
        this.email = email;
        this.hashPassword(password);
        this.groupNumber=groupNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void hashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}