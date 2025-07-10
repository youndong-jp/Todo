import java.util.*;
public class MemoryTodoRepository implements TodoRepository {
    private final List<Todo> todos = new ArrayList<>();

    @Override
    public List<Todo>findAll() {
        return new ArrayList<>(todos);
    }

    @Override
    public Todo findById(int id) {
        return todos.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }
    @Override
    public void save(Todo todo){
        todos.add(todo);
    }
    @Override
    public void delete(int id){
        todos.removeIf(t ->t.getId() == id );
    }
    @Override
    public void update(int id,Todo updateTodo){
        delete(id);
        save(updateTodo);
    }
}
