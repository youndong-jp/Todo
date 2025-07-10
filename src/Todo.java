import java.time.LocalDate;

    public class Todo {
        private String task; // 할 일
        private boolean isDone;// 끝난 일
        private LocalDate dueDate;
        private String category;
        private int id;
        public static int nextId =1;

        public Todo(String task,boolean isDone,String category ,LocalDate dueDate) {
            this.task = task;
            this.isDone = isDone;
            this.category = category;
            this.dueDate = dueDate;
            this.id = nextId++;
        }
        public int getId() {
            return id;
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
        return checkbox +""+ task +"("+category+")" +due;
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
    public LocalDate getDueDate() {
        return dueDate;
    }
    public String getCategory() {
        return category;
    }
}
