import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeParseException;

public class TodoService {
    private List<Todo> todos;

    public TodoService(List<Todo> todos) {
        this.todos = todos;
    }

    public void add(String task, String inputDate) {
        LocalDate dueDate;

        if (inputDate == null || inputDate.trim().isEmpty()) {
            dueDate = LocalDate.now();  // 기본값
        } else {
            try {
                dueDate = LocalDate.parse(inputDate);
            } catch (DateTimeParseException e) {
                System.out.println("⚠️ 마감일 형식이 잘못되었습니다. 오늘 날짜로 설정합니다.");
                dueDate = LocalDate.now();
            }
        }

        todos.add(new Todo(task, false, dueDate));
        System.out.println("할 일이 추가되었습니다.");
    }

    public void printAll() {
        if (todos.isEmpty()) {
            System.out.println("오늘 할 일이 없습니다.");
        } else {
            for (int i = 0; i < todos.size(); i++) {
                System.out.println((i + 1) + "." + todos.get(i));
            }
        }
    }

    public void markDone(int id) {
        int realIndex = id - 1;
        if (isValidIndex(realIndex)) {
            todos.get(realIndex).markDone();
            System.out.println("✅ 완료되었습니다.");
        } else {
            System.out.println("❌ 잘못된 번호입니다.");
        }
    }


    public void remove(int displayIndex) {
        int realIndex = displayIndex - 1;
        if (isValidIndex(realIndex)) {
            for (int i = 0; i < todos.size(); i++) {
                System.out.println((i + 1) + "." + todos.get(i));
            }
            todos.remove(realIndex);
            System.out.println("✅ 삭제되었습니다.");
        } else {
            System.out.println("❌ 잘못된 번호입니다.");
        }
    }

    public List<Todo> getTodos() {
        return todos;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < todos.size();
    }

    public void removeStrict(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("삭제할 수 없는 인덱스입니다: " + index);
        }
        todos.remove(index);
    }

    public boolean isEmpty() {
        return todos == null || todos.isEmpty();
    }

    public void printIncomplete() {
        boolean hasIncomplete = false;
        for (int i = 0; i < todos.size(); i++) {
            if (!todos.get(i).isDone()) {
                System.out.println((i + 1) + ". " + todos.get(i));
                hasIncomplete = true;
            }
        }
        if (!hasIncomplete) {
            System.out.println("✅ 완료할 일이 없습니다.");
        }
    }

}
