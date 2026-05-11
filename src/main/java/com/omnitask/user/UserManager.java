package com.omnitask.user;
import com.omnitask.user.UserItem;
import com.omnitask.user.User;
import java.io.*;
import org.mindrot.jbcrypt.BCrypt;

public class UserManager {

    private static final String DIR    = "Login/";
    private static final String PREFIX = "users_";
    private static final String EXT    = ".txt";

    private static String getFile(String username) {
        return DIR + PREFIX + username + EXT;
    }

    private static void ensureDir() {
        File d = new File(DIR);
        if (!d.exists()) d.mkdirs();
    }

    // Load a user from file into a UserItem object
    private static User loadUser(String username) throws IOException {
        File f = new File(getFile(username));
        if (!f.exists()) return null;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = br.readLine();
            if (line == null || line.isBlank()) return null;
            return UserItem.fromFileLine(line);
        }
    }

    // Save a user object back to its file
    private static void saveUser(User user) throws IOException {
        ensureDir();
        try (PrintWriter pw = new PrintWriter(new FileWriter(getFile(user.getUsername()), false))) {
            pw.println(user.toFileString());
        }
    }

    public static boolean isValidUsername(String username) {
        return username != null && username.matches("[a-zA-Z0-9_]+");
    }

    public static boolean isUserExists(String username) throws IOException {
        return new File(getFile(username)).exists();
    }

    public static boolean checkLoginCredentials(String username, String password) throws IOException {
        User user = loadUser(username);
        if (user == null) return false;
        return user.getUsername().equals(username)
                && BCrypt.checkpw(password, user.getHashedPassword());
    }

    public static boolean register(String username, String password,
                                   String passHint, String favAnswer) throws IOException {
        if (!isValidUsername(username)) return false;
        if (isUserExists(username))     return false;

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        saveUser(new UserItem(username, hashed, passHint, favAnswer));
        return true;
    }

    public static String getPassHint(String username) throws IOException {
        User user = loadUser(username);
        return user != null ? user.getPassHint() : null;
    }

    public static String getFavAnswer(String username) throws IOException {
        User user = loadUser(username);
        return user != null ? user.getFavAnswer() : null;
    }

    public static boolean resetPassword(String username, String newPassword) throws IOException {
        User user = loadUser(username);
        if (user == null) return false;
        user.setHashedPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        saveUser(user);
        return true;
    }
}
