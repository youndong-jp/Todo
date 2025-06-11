public class Todo {
    private String task; // 할 일
    private boolean isDone; // 끝난 일

    public Todo(String task,boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }
    @Override
    public String toString() {
        String checkbox = isDone ? "[x]" : "[]";
        return checkbox +""+ task;
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
