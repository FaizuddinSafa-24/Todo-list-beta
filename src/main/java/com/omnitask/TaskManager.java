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

   

    private static List<Task> loadTasks(String username) throws IOException {
        List<Task> tasks = new ArrayList<>();
        File f = new File(getTaskFile(username));
        if (!f.exists()) return tasks;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) {
                    tasks.add(TaskItem.fromFileLine(line));
                }
            }
        }
        return tasks;
    }

    // BUG FIX: just iterate and write the list you were given — no re-load, no == comparison
    private static void saveTasks(String username, List<Task> tasks) throws IOException {
        ensureDir();
        try (PrintWriter pw = new PrintWriter(new FileWriter(getTaskFile(username), false))) {
            for (Task t : tasks) {
                pw.println(t.toFileString());
            }
        }
    }

    // ------------------------------------------------------------------ //
    //  Public API                                                          //
    // ------------------------------------------------------------------ //

    public static boolean addTask(String username, String title,
                                  String text, String dueDate) throws IOException {
        if (title == null || title.isBlank()) return false;
        ensureDir();
        // Append one line — no need to reload everything
        try (PrintWriter pw = new PrintWriter(new FileWriter(getTaskFile(username), true))) {
            pw.println(new TaskItem(title, text, dueDate, false).toFileString());
        }
        return true;
    }

    // Returns raw String[] list so existing UI code keeps working unchanged
    public static List<String[]> loadTask(String username) throws IOException {
        List<String[]> result = new ArrayList<>();
        for (Task t : loadTasks(username)) {
            result.add(t.toFileString().split("\\|", -1));
        }
        return result;
    }

    public static void deleteTask(String username, int index) throws IOException {
        List<Task> tasks = loadTasks(username);
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasks(username, tasks);
        }
    }

    public static void markDone(String username, int index) throws IOException {
        List<Task> tasks = loadTasks(username);
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markDone();   // uses interface method
            saveTasks(username, tasks);    // now actually saves correctly
        }
    }
}
