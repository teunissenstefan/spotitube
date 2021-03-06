package dev.stefanteunissen.restserver.models;

import org.apache.commons.codec.digest.DigestUtils;

public class User {
    private int id;
    private String user;
    private String password;

    public User(int id, String user, String password) {
        this.setId(id);
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Check if the entered password hash matches the pashword hash from the database
     *
     * @param enteredPassword
     * @return
     */
    public boolean passwordCheck(String enteredPassword) {
//        System.out.println(DigestUtils.sha256Hex(enteredPassword));
        return DigestUtils.sha256Hex(enteredPassword).equals(this.password);
//        return this.password.equals(enteredPassword);
    }
}
