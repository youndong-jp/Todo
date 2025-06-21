import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        todosToSave.add(new Todo("JUnit 복습", false,"기타", LocalDate.now()));
        todosToSave.add(new Todo("깃 정리", true,"기타", LocalDate.now()));

        // when
        TodoManager.saveTodosToFile(todosToSave, testFilename);
        List<Todo> loadedTodos = TodoManager.loadTodosFromFile(testFilename);

        // then
        assertEquals(2, loadedTodos.size());
        assertEquals("JUnit 복습", loadedTodos.get(0).getTask());
        assertFalse(loadedTodos.get(0).isDone());
        assertEquals("기타", loadedTodos.get(0).getCategory());
        assertNotEquals(LocalDate.of(2024,11,14), loadedTodos.get(1).getDueDate());

        assertEquals("깃 정리", loadedTodos.get(1).getTask());
        assertTrue(loadedTodos.get(1).isDone());
        assertEquals("기타", loadedTodos.get(1).getCategory());
        assertNotEquals(LocalDate.of(2024,11,14), loadedTodos.get(1).getDueDate());
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

    @Test
    public void testExportCsvCreateValidFile() {
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo("JUnit 복습", false,"기타", LocalDate.now()));
        String filename = "test_todos.csv";

        TodoManager.exportCsv(todos, filename);

        File file = new File(filename);
        assertTrue(file.exists());

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String header = reader.readLine();
            String data = reader.readLine();

            assertEquals("ID,isDone,Task(Category),DueDate", header);
            assertTrue(data.contains("JUnit 복습"));
            assertTrue(data.contains("기타"));
            assertTrue(data.contains("[]"));
        }catch(Exception e){
            fail("파일 읽기 실패" + e.getMessage());
        }

    }
}
