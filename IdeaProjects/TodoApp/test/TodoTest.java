import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("공부하기", false);
        assertEquals("공부하기", todo.getTask());
        assertFalse(todo.isDone());
    }

    @Test
    public void testMarkDone() {
        Todo todo = new Todo("운동하기", false);
        todo.markDone();
        assertTrue(todo.isDone());
    }
}
