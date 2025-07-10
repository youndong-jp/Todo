import java.util.List;

public class TodoService{
    private final TodoRepository repository;

    public TodoService(TodoRepository repository){
        this.repository = repository;
    }

    public void createTodo(Todo todo){
        repository.save(todo);
    }
    public List<Todo>getAllTodos(){
        return repository.findAll();
    }
    public void deleteTodo(int id){
        repository.delete(id);
    }
    public void updateTodo(int id ,Todo newTodo){
        repository.update(id, newTodo);
    }
    public Todo getTodoById(int id){
        return repository.findById(id);
    }
}