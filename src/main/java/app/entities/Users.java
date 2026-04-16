package app.entities;

import java.util.Objects;

public class Users {
    private String name;
    private String user_email;
    private String password;
    private int user_id;
    private int balance;
    private boolean is_admin;

    public Users(String name, String email, String password, int user_id, int balance, boolean is_admin) {
        this.name = name;
        this.user_email = email;
        this.password = password;
        this.user_id = user_id;
        this.balance = balance;
        this.is_admin = is_admin;
    }


    public String getName() {
        return name;
    }

    public void setFirstname(String name) {
        this.name = name;
    }

    public String getUseremail() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getbalance() {
        return balance;
    }

    public boolean isAdmin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
    public int getUserId() {
        return user_id;
    }

    public String getEmail() {
        return user_email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return user_id == users.user_id && Objects.equals(name, users.name) && Objects.equals(user_email, users.user_email) && Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, user_email, password, user_id);
    }
}

