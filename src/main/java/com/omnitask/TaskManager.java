package com.omnitask;

import java.io.*;
import java.util.*;

public class TaskManager {

    private static final String DIR = "Tasks/";

    private static String getTaskFile(String username) {
        return DIR + "tasks_" + username + ".txt";
    }

    private static void ensureDir() {
        File d = new File(DIR);
        if (!d.exists()) d.mkdirs();
    }

    // Format: title|text|dueDate|done
    public static boolean addTask(String username, String title, String text, String dueDate) throws IOException {
        if (title == null || title.isBlank()) return false;
        ensureDir();
        try (PrintWriter pw = new PrintWriter(new FileWriter(getTaskFile(username), true))) {
            pw.println(title + "|" + text + "|" + dueDate + "|false");
        }
        return true;
    }

    public static List<String[]> loadTask(String username) throws IOException {
        List<String[]> tasks = new ArrayList<>();
        File f = new File(getTaskFile(username));
        if (!f.exists()) return tasks;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) tasks.add(line.split("\\|", -1));
            }
        }
        return tasks;
    }

    private static void saveTasks(String username, List<String[]> tasks) throws IOException {
        ensureDir();
        try (PrintWriter pw = new PrintWriter(new FileWriter(getTaskFile(username), false))) {
            for (String[] t : tasks) pw.println(String.join("|", t));
        }
    }

    public static void deleteTask(String username, int index) throws IOException {
        List<String[]> tasks = loadTask(username);
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasks(username, tasks);
        }
    }

    public static void markDone(String username, int index) throws IOException {
        List<String[]> tasks = loadTask(username);
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index)[3] = "true";
            saveTasks(username, tasks);
        }
    }
}