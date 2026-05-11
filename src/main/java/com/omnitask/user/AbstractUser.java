package com.omnitask.user;

public abstract class AbstractUser implements User {

    protected String username;
    protected String hashedPassword;
    protected String passHint;
    protected String favAnswer;

    public AbstractUser(String username, String hashedPassword, String passHint, String favAnswer) {
        this.username       = username;
        this.hashedPassword = hashedPassword;
        this.passHint       = passHint;
        this.favAnswer      = favAnswer;
    }

    @Override
    public String getUsername()        { return username; }

    @Override
    public String getHashedPassword()  { return hashedPassword; }

    @Override
    public String getPassHint()        { return passHint; }

    @Override
    public String getFavAnswer()       { return favAnswer; }

    @Override
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    @Override
    public String toFileString() {
        return username + "|" + hashedPassword + "|" + passHint + "|" + favAnswer;
    }

    public abstract String getUserType();
}
