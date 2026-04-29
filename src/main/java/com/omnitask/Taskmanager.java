package com.omnitask;

  

    public class Taskmanager {

        private static final String DIR = "Tasks/";

        private static String getDir(String username) {
            return DIR + username + "/";
        }

        private static String getTaskFile(String username, String title) {
            return getDir(username) + title + ".txt";
        }

        private static void ensureDir(String username) {
            File d = new File(getDir(username));
            if (!d.exists()) {
                d.mkdirs();
            }
        }

        private static void ensureTask(String username, String title) {
            File d = new File(getTaskFile(username, title));
            if (!d.exists()) {
                d.mkdirs();
            }
        }

        private static String[] parseTaskFile(File f) throws IOException {
            Map<String, String> map = new LinkedHashMap<>();
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        map.put(parts[0], parts[1]);
                    }
                }
            }
            return new String[]{
                map.getOrDefault("title", ""),
                map.getOrDefault("text", ""),
                map.getOrDefault("due", ""),
                map.getOrDefault("done", "false"),
                map.getOrDefault("type", "text")
            };
        }

        public static boolean addTask(String username, String title, String text, String dueDate, String type) throws IOException {
            if (title == null || title.isBlank()) {
                return false;
            }
            ensureDir(username);
            File f = new File(getTaskFile(username, title));
            try (PrintWriter pw = new PrintWriter(new FileWriter(f, false))) {
                pw.println("title=" + title);
                pw.println("text=" + text);
                pw.println("due=" + dueDate);
                pw.println("type=" + type);
                pw.println("done=false");
            }
            return true;
        }

        public static List<String[]> loadTask(String username) throws IOException {
            List<String[]> result = new ArrayList<>();
            File dir = new File(getDir(username));
            if (!dir.exists()) {
                return result;
            }
            File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
            if (files == null) {
                return result;
            }
            for (File f : files) {
                result.add(parseTaskFile(f));
            }
            return result;
        }

        public static void deleteTask(String username, String title) throws IOException {
            File f = new File(getTaskFile(username, title));
            if (f.exists()) {
                f.delete();
            }
        }

        public static void updateTask(String username, String title, String text, String dueDate, String type) throws IOException {
            //List<String[]> taskFiles = loadTask(username);
            try (BufferedReader br = new BufferedReader(new FileReader(getTaskFile(username, title)))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("=", 2);
                    if (!parts[1].equals(username)) {
                    }
                }
            }
        }

        //addTask(username, title, text, dueDate, type);
    }

    public static void markDone(String username, String title,
            boolean done) throws IOException {
        File f = new File(getTaskFile(username, title));
        if (!f.exists()) {
            return;
        }
        String[] task = parseTaskFile(f);
        try (PrintWriter pw = new PrintWriter(new FileWriter(f, false))) {
            pw.println("title=" + task[0]);
            pw.println("text=" + task[1]);
            pw.println("due=" + task[2]);
            pw.println("type=" + task[4]);
            pw.println("done=" + done);
        }
    }
}
