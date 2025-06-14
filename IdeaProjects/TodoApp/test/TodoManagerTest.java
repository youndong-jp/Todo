import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TodoManagerTest {

    private final String testFilename = "test_todos.json";

    @BeforeEach
    public void setUp() {
        // 테스트 전 기존 파일 있으면 삭제
        File file = new File(testFilename);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    public void tearDown() {
        // 테스트 후 파일 삭제
        File file = new File(testFilename);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveAndLoadTodos() {
        // given
        List<Todo> todosToSave = new ArrayList<>();
        todosToSave.add(new Todo("JUnit 복습", false, LocalDate.now()));
        todosToSave.add(new Todo("깃 정리", true, LocalDate.now()));

        // when
        TodoManager.saveTodosToFile(todosToSave, testFilename);
        List<Todo> loadedTodos = TodoManager.loadTodosFromFile(testFilename);

        // then
        assertEquals(2, loadedTodos.size());
        assertEquals("JUnit 복습", loadedTodos.get(0).getTask());
        assertFalse(loadedTodos.get(0).isDone());

        assertEquals("깃 정리", loadedTodos.get(1).getTask());
        assertTrue(loadedTodos.get(1).isDone());
    }

    @Test
    public void testLoadFromMissingFile() {
        // given
        String missingFile = "not_exist_file.json";

        // when
        List<Todo> result = TodoManager.loadTodosFromFile(missingFile);

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
