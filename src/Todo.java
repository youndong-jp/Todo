import java.time.LocalDate;

public class Todo {
    private String task; // 할 일
    private boolean isDone;// 끝난 일
    private LocalDate dueDate;
    private String category;

    public Todo(String task,boolean isDone,String category ,LocalDate dueDate) {
        this.task = task;
        this.isDone = isDone;
        this.category = category;
        this.dueDate = dueDate;
    }
    public String getStatus() {
        if (dueDate == null) return "";

        LocalDate today = LocalDate.now();

        if (dueDate.isBefore(today)) {
            return "OVERDUE";
        } else if (dueDate.isEqual(today)) {
            return "D-DAY";
        } else {
            int daysLeft = today.until(dueDate).getDays();
            return "D-" + daysLeft;
        }
    }

    @Override
    public String toString() {
        String checkbox = isDone ? "[x]" : "[]";
        String due = dueDate != null ? "(마감일 " + dueDate + ","+ getStatus() + ")" : "";
        return checkbox +""+ task +due;
    }
    public String getTask() {
        return task;
    }

    public void markDone() {
        isDone = true;
    }
    public boolean isDone() {
        return isDone;
    }
}
