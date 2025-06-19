import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("공부하기", false,"기타" ,LocalDate.now());
        assertEquals("공부하기", todo.getTask());
        assertEquals("기타",todo.getCategory());
        assertEquals(LocalDate.now(),todo.getDueDate());
        assertFalse(todo.isDone());
    }

    @Test
    public void testMarkDone() {
        Todo todo = new Todo("운동하기", false,"기타" ,LocalDate.now());
        todo.markDone();
        assertTrue(todo.isDone());
    }

    @Test
    public void testNullPointerException(){
        Todo todo = null;
        assertThrows(NullPointerException.class, () -> {});
    }
}
