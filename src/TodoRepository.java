import java.util.List;
public interface TodoRepository {
    List<Todo> findAll();
    Todo findById(int id);
    void save(Todo todo);
    void delete(int id);
    void update(int id,Todo updateTodo);
}
