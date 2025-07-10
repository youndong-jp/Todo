import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class TodoService {
    private final TodoRepository repository;
    private final Stack<Todo> deleteStack = new Stack<>();

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public String createTodo(String task, String category, String inputDate) {
        LocalDate dueDate;
        try {
            dueDate = (inputDate == null || inputDate.isBlank())
                    ? LocalDate.now()
                    : LocalDate.parse(inputDate);
        } catch (DateTimeParseException e) {
            dueDate = LocalDate.now();
        }
        if (category == null || category.isBlank()) {
            category = "기타";
        }
        Todo todo = new Todo(task, false, category, dueDate);
        repository.save(todo);
        return "할일이 추가 되었습니다";
    }

    public String getAllTodos() {
        List<Todo> list = repository.findAll();
        if (list.isEmpty()) {
            return "할일이 없습니다";
        }
        return list.stream()
                .map(Todo::toString)
                .collect(Collectors.joining("\n"));

    }

    public String deleteTodo(int id) {
        Todo todo = repository.findById(id);
        if (todo ==null){return"해당일이 없습니다";}
        deleteStack.push(todo);
        repository.delete(id);
        return"삭제완료";
    }

    public String updateTodo(int id, String task, String category, String inputDate) {
        Todo old = repository.findById(id);
        if (old == null) {return "해당일이 없습니다";}
        LocalDate dueDate;
        try{
            dueDate = (inputDate==null ||inputDate.isBlank())
                    ? LocalDate.now()
                    : LocalDate.parse(inputDate);
        }catch (DateTimeParseException e){
            dueDate = old.getDueDate();
        }
        Todo updated = new Todo(task, old.isDone(),category, dueDate);
        repository.update(id, updated);
        return"할일이 수정되었습니다";
    }

    public String getTodoById(int id) {
        Todo todo = repository.findById(id);
        if (todo == null) {
            return "해당 할 일이 없습니다";
        }
        return todo.toString();

    }
    public String markDone(int id) {
        Todo todo = repository.findById(id);
        if(todo == null){return"해당 할일이 없습니다";}
        todo.markDone();
        return"완료처리 되었습니다";
    }
    public String undoDelete(int id) {
        if(deleteStack.isEmpty()){return"삭제된 항목이 없습니다";}
        Todo restored =deleteStack.pop();
        repository.save(restored);
        return"복구완료";
    }
    public String getIncompleteTodos() {
        List<Todo> list = repository.findAll().stream()
                .filter(todo -> !todo.isDone())
                .collect(Collectors.toList());
        if (list.isEmpty()) return " 완료할 일이 없습니다.";
        return list.stream()
                .map(Todo::toString)
                .collect(Collectors.joining("\n"));
    }

    public String searchByKeyword(String keyword) {
        List<Todo> result = repository.findAll().stream()
                .filter(todo -> todo.getTask().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        if (result.isEmpty()) return " 검색 결과가 없습니다.";
        return result.stream()
                .map(Todo::toString)
                .collect(Collectors.joining("\n"));
    }

    public boolean isEmpty() {
        return repository.findAll().isEmpty();
    }
}