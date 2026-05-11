package com.omnitask.user;

import com.omnitask.user.AbstractUser;

public class UserItem extends AbstractUser {

    public UserItem(String username, String hashedPassword, String passHint, String favAnswer) {
        super(username, hashedPassword, passHint, favAnswer);
    }

    // Parse one line from the file into a UserItem
    public static UserItem fromFileLine(String line) {
        String[] parts     = line.split("\\|", -1);
        String username    = parts.length > 0 ? parts[0] : "";
        String hashedPass  = parts.length > 1 ? parts[1] : "";
        String passHint    = parts.length > 2 ? parts[2] : "";
        String favAnswer   = parts.length > 3 ? parts[3] : "";
        return new UserItem(username, hashedPass, passHint, favAnswer);
    }

    @Override
    public String getUserType() { return "Standard"; }
}
