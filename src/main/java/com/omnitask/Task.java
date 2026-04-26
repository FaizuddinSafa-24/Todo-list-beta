public interface Task {


    String getTitle();
    String getText();
    String getDueDate();
    boolean isDone();
    void markDone();
    String toFileString();
}
