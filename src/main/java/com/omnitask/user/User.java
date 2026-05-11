package com.omnitask.user;
public interface User {

    String getUsername();
    String getHashedPassword();
    String getPassHint();
    String getFavAnswer();
    void setHashedPassword(String hashedPassword);
    String toFileString();
}
