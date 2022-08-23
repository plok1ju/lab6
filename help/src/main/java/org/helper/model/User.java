package org.helper.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 16;

    private int id;

    private String login;
    private String password;


    public User(String login, String password, int id) {
        this.login = login;
        this.id = id;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
