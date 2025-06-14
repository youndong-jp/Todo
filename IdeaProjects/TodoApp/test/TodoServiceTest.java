import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TodoServiceTest {

    private TodoService service;

    @BeforeEach
    public void setUp() {
        ArrayList<Todo> todos = new ArrayList<>();
        service = new TodoService(todos);
    }
    @Test
    public void testInvalidIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            service.removeStrict(0);
        });
    }
    @Test
    public void testAddTodo() {
        service.add("복습하기","2025-12-14");
        assertEquals(1, service.getTodos().size());
        assertEquals("복습하기", service.getTodos().get(0).getTask());
    }

    @Test
    public void testMarkDone() {
        service.add("청소하기","2025-12-14");
        service.markDone(0);
        assertTrue(service.getTodos().get(0).isDone());
    }

    @Test
    public void testRemoveTodo() {
        service.add("휴식","2025-12-14");
        service.remove(0);
        assertEquals(0, service.getTodos().size());
    }

    @Test
    public void testInvalidIndex() {
        service.markDone(10); // index out of bounds
        service.remove(10);   // index out of bounds
        assertEquals(0, service.getTodos().size()); // 여전히 아무것도 없어야 함
    }
}
