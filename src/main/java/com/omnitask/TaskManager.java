package com.omnitask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author safa
 */
public class TaskManager {

    private static String getTaskFile(String username) {
        return "Tasks/"+"tasks_" + username + ".txt";
    }

    /**
     *
     * @param username
     * @param title
     * @param priority
     * @param dueDate
     * @return
     * @throws IOException
     */
    public static boolean addTask(String username, String title, String priority, String dueDate) throws IOException {
        if (title.isEmpty()) {
            return false;
        }
        try (PrintWriter write = new PrintWriter(new FileWriter(getTaskFile(username), true))) {
            write.println(title + "|" + priority + "|" + dueDate + "|" + "false");
        }
        return true;
    }

    /**
     *
     * @param username
     * @return
     * @throws IOException
     */
    public static List<String[]> loadTask(String username) throws IOException {
        List<String[]> tasks = new ArrayList<>();
        File f = new File(getTaskFile(username));
        if (!f.exists()) {
            return tasks;
        }
        try (BufferedReader buffer = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                tasks.add(line.split("\\|"));
            }
        }
        return tasks;
    }

    /**
     *
     * @param username
     * @param index
     * @throws IOException
     */
    public static void markDone(String username, int index) throws IOException {
        List<String[]> tasks = loadTask(username);
        tasks.get(index)[3] = "true";

    }

    private static void saveTasks(String username, List<String[]> tasks) throws IOException {
        try (PrintWriter write = new PrintWriter(new FileWriter(getTaskFile(username), false))) {
            for (String[] task : tasks) {

                write.println(String.join("|", task));
            }
        }
    }

    /**
     *
     * @param username
     * @param index
     * @throws IOException
     */
    public static void deleteTask(String username, int index) throws IOException {
        List<String[]> tasks = loadTask(username);
        tasks.remove(index);
        saveTasks(username, tasks);
    }
}
