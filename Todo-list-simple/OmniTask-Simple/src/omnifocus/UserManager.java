/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package omnifocus;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author safa
 */
public class UserManager {

    public static void main(String[] args) {
        try {
          //  PrintWriter write = new PrintWriter(new FileWriter("Users.txt", true));
            //write.close();
            System.out.println("Hello");
            String name = "Safa";
            String pass = "ZAhlen";
            setUserInfo(name, pass);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void setUserInfo(String username, String pass) throws IOException {
        String passHashed = BCrypt.hashpw(pass, BCrypt.gensalt());
        System.out.println("passHashed");
        System.out.println(passHashed);
        System.out.println("loaded");
        try(PrintWriter write = new PrintWriter(new FileWriter("Files/Users.txt", true))) {

            //PrintWriter write = new PrintWriter(new FileWriter("Users.txt", true));
            write.println(username);
            write.println(passHashed);
            boolean match = checkEqualPass(pass, passHashed);
            System.out.println(match);
        }
    }

    public static boolean checkEqualPass(String pass, String passHashed) {
        boolean match = BCrypt.checkpw(pass, passHashed);
        return match;
    }
}
