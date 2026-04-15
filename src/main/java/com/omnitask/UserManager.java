package com.omnitask;

import java.io.*;
import org.mindrot.jbcrypt.BCrypt;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author safa
 */
public class UserManager {

    private static final String dir = "Login/";
    private static final String FILE1 = "tasks_";
    private static final String FILE2 = ".txt";

    public static boolean isValidUsername(String username) {
        return username.matches("[a-zA-Z0-9_]+");
    }

    public static boolean isUserExists(String username) throws IOException {
        File file = new File(dir + FILE1 + username + FILE2);
        if (!file.exists()) {
            return false;
        }
        try (BufferedReader buffer = new BufferedReader(new FileReader(dir + FILE1 + username + FILE2));) {
            String partition;
            while ((partition = buffer.readLine()) != null) {
                String[] part = partition.split("\\|");
                if (part[0].equals(username)) {
                    return true;
                }
            }
            return false;

        }

    }

    public static boolean checkLoginCredentials(String username, String password) throws IOException {
        File f = new File(FILE1 + username + FILE2);
        if (!f.exists()) {
            System.out.println("File not exists.");
            return false;
        }
        try (BufferedReader buffer = new BufferedReader(new FileReader(dir + FILE1 + username + FILE2))) {
            String stage;
            while ((stage = buffer.readLine()) != null) {
                String[] parts = stage.split("\\|");
                if (parts[0].equals(parts[0]) && BCrypt.checkpw(password, parts[1])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean register(String username, String password) throws IOException {
        File file = new File(dir + FILE1 + username + FILE2);
        if (!isValidUsername(username)) {
            return false;
        }
        if (isUserExists(username)) {
            System.out.println("Username already exists");
            return false;
        }

        String passhashed = BCrypt.hashpw(password, BCrypt.gensalt());

        try (PrintWriter w = new PrintWriter(new FileWriter(dir + FILE1 + username + FILE2, true))) {
            w.println(username + "|" + passhashed);
        }
        return true;
    }
}
