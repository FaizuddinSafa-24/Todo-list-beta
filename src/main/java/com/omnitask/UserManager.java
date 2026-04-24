package com.omnitask;

import java.io.*;
import org.mindrot.jbcrypt.BCrypt;

public class UserManager {

    private static final String DIR = "Login/", PREFIX = "tasks_",  EXT = ".txt";

    // File format per line: username|hashedPassword|passHint|favAnswer
    private static String getFile(String username) {
        return DIR + PREFIX + username + EXT;
    }

    public static boolean isValidUsername(String username) {
        return username != null && username.matches("[a-zA-Z0-9_ ]+");
    }

    public static boolean isUserExists(String username) throws IOException {
        File f = new File(getFile(username));
        return f.exists();
    }

    public static boolean checkLoginCredentials(String username, String password) throws IOException {
        File f = new File(getFile(username));
        if (!f.exists()) return false;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = br.readLine();
            if (line == null) return false;
            String[] parts = line.split("\\|", -1);
            // parts[0]=username, parts[1]=hashedPass, parts[2]=hint, parts[3]=favAnswer
            return parts[0].equals(username) && BCrypt.checkpw(password, parts[1]);
        }
    }

    // Register: stores username|hashedPass|passHint|favAnswer
    public static boolean register(String username, String password, String passHint, String favAnswer) throws IOException {
        if (!isValidUsername(username)) return false;
        if (isUserExists(username)) return false;

        File dir = new File(DIR);
        if (!dir.exists()) dir.mkdirs();

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        try (PrintWriter pw = new PrintWriter(new FileWriter(getFile(username), false))) {
            pw.println(username + "|" + hashed + "|" + passHint + "|" + favAnswer);
        }
        return true;
    }

    // Returns passHint string for the user, null if not found
    public static String getPassHint(String username) throws IOException {
        File f = new File(getFile(username));
        if (!f.exists()) return null;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = br.readLine();
            if (line == null) return null;
            String[] parts = line.split("\\|", -1);
            return parts.length > 2 ? parts[2] : null;
        }
    }

    // Returns favAnswer for verification, null if not found
    public static String getFavAnswer(String username) throws IOException {
        File f = new File(getFile(username));
        if (!f.exists()) return null;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = br.readLine();
            if (line == null) return null;
            String[] parts = line.split("\\|", -1);
            return parts.length > 3 ? parts[3] : null;
        }
    }

    // Resets password: rewrites file with new hashed password
    public static boolean resetPassword(String username, String newPassword) throws IOException {
        File f = new File(getFile(username));
        if (!f.exists()) return false;
        String hint = getPassHint(username);
        String favAnswer = getFavAnswer(username);
        String hashed = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        try (PrintWriter pw = new PrintWriter(new FileWriter(f, false))) {
            pw.println(username + "|" + hashed + "|" + (hint != null ? hint : "") + "|" + (favAnswer != null ? favAnswer : ""));
        }
        return true;
    }
}