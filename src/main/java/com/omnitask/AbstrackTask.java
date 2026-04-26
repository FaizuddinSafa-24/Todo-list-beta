public abstract class AbstrackTask implements Task{


    protected String title;
    protected String text;
    protected String dueDate;
    protected boolean done;

    public AbstrackTask(String title, String text, String dueDate, boolean done) {
        this.title   = title;
        this.text    = text;
        this.dueDate = dueDate;
        this.done    = done;
    }

    @Override
    public String getTitle()   { return title; }

    @Override
    public String getText()    { return text; }

    @Override
    public String getDueDate() { return dueDate; }

    @Override
    public boolean isDone()    { return done; }

    @Override
    public void markDone()     { this.done = true; }

    @Override
    public String toFileString() {
        return title + "|" + text + "|" + dueDate + "|" + done;
    }


    public abstract String getTaskType();
}
