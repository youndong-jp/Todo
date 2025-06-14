import java.time.LocalDate;

public class Todo {
    private String task; // 할 일
    private boolean isDone;// 끝난 일
    private LocalDate dueDate;

    public Todo(String task,boolean isDone,LocalDate dueDate) {
        this.task = task;
        this.isDone = isDone;
        this.dueDate = dueDate;
    }
    @Override
    public String toString() {
        String checkbox = isDone ? "[x]" : "[]";
        String due = dueDate != null ? "(마감일" + dueDate + ")" : "";
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
