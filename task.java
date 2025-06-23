import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private String title;
    private String priority;
    private LocalDate dueDate;
    private boolean isCompleted;

    public Task(String title, String priority, LocalDate dueDate) {
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public String getTitle() { return title; }
    public String getPriority() { return priority; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isCompleted() { return isCompleted; }

    public void setCompleted(boolean status) { this.isCompleted = status; }

    @Override
    public String toString() {
        return "[" + priority + "] " + title + " (Due: " + dueDate + ") " +
               (isCompleted ? "✓" : "✗");
    }
}
