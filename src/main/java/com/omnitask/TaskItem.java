public class TaskItem extends AbstrackTask{


    public TaskItem(String title, String text, String dueDate, boolean done) {
        super(title, text, dueDate, done);
    }

    // Parse one line from the file into a TaskItem
    public static TaskItem fromFileLine(String line) {
        String[] parts = line.split("\\|", -1);
        String title   = parts.length > 0 ? parts[0] : "";
        String text    = parts.length > 1 ? parts[1] : "";
        String dueDate = parts.length > 2 ? parts[2] : "";
        boolean done   = parts.length > 3 && parts[3].equalsIgnoreCase("true");
        return new TaskItem(title, text, dueDate, done);
    }

    @Override
    public String getTaskType() { return "Standard"; }
}
