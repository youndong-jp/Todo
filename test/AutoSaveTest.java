import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
public class AutoSaveTest {
    private static final String FILENAME = "todosTest.json";
    private TodoService service;
    private List<Todo> todos;

    @BeforeEach
    void setup() {
        todos = new ArrayList<>();
        service = new TodoService(todos,FILENAME);
        new File(FILENAME).delete();
    }
    @Test
    void testAddTodoAutoSave(){
        service.add("Test","테스트", LocalDate.now().toString());
        List<Todo> loaded = TodoManager.loadTodosFromFile(FILENAME);
        assertEquals(1,loaded.size());
    }

    @Test
    void testRemoveTodoAutoSave(){
        service.add("Test","테스트", LocalDate.now().toString());
        service.remove(1);
        assertEquals(0,TodoManager.loadTodosFromFile(FILENAME).size());
    }
    @Test
    void testMarkDoneTodoAutoSave(){
        service.add("Test","테스트", LocalDate.now().toString());
        service.markDone(1);
        List<Todo> loaded = TodoManager.loadTodosFromFile(FILENAME);
        assertEquals(1,loaded.size());
        assertTrue(loaded.get(0).isDone());
    }
}
