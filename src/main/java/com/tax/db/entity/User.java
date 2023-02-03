package com.tax.db.entity;

import java.io.Serializable;
import java.util.regex.Pattern;

public class User implements Serializable {
    private int id;

    private String username;

    private String password;

    private String email;

    private String dateRegistry;

    private Integer roleId;

    public User(String username, String password, String email) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("User Id cant be < 0 and >"+ Integer.MAX_VALUE);
        }
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.isBlank()) {
            throw new IllegalArgumentException("Illegal username value, current value is blank");
        } else if (username.length() > 16) {
            throw new IllegalArgumentException("Illegal username value, current value is long (" + username + ")");
        } else if (username.length() < 4) {
            throw new IllegalArgumentException("Illegal username value, current value is short (" + username + ")");
        } else {
            this.username = username;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.isBlank()) {
            throw new IllegalArgumentException("Illegal password value, current value is blank");
        } else if (password.length() > 32) {
            throw new IllegalArgumentException("Illegal password value, current value is long (" + password + ")");
        } else if (password.length() < 4) {
            throw new IllegalArgumentException("Illegal password value, current value is short (" + password + ")");
        } else {
            this.password = password;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email != null && pat.matcher(email).matches()) {
            this.email = email;
        } else throw new IllegalArgumentException("Illegal email value");
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        if (roleId >= 0) {
            this.roleId = roleId;
        } else throw new IllegalArgumentException("Illegal roleId value. RoleId cant be < 0");
    }

    public String getDateRegistry() {
        return dateRegistry;
    }

    public void setDateRegistry(String dateRegistry) {
        this.dateRegistry = dateRegistry;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dateRegistry='" + dateRegistry + '\'' +
                ", role_id='" + roleId + '\'' +
                '}';
    }
}
