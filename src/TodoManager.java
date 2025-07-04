import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class TodoManager {

    private static Gson buildGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
    }

    public static void saveTodosToFile(List<Todo> todos, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            Gson gson = buildGson();
            gson.toJson(todos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Todo> loadTodosFromFile(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            Gson gson = buildGson();
            Type type = new TypeToken<List<Todo>>() {}.getType();
            List<Todo> todos = gson.fromJson(reader, type);
            return (todos != null) ? todos : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void exportCsv(List<Todo>todos, String filename) {
        try(FileWriter writer = new FileWriter(filename)){
            writer.append("ID,isDone,Task(Category),DueDate\n");
            for (int i=0;i<todos.size();i++) {
                Todo todo = todos.get(i);
                writer.append(String.valueOf(i+1)).append(",");
                writer.append(todo.toString()).append("\n");
            }
            System.out.println("todos.csv 파일");
            } catch (IOException e) {
            System.out.println("CSV 저장  오류 발생 "+e.getMessage());
        }
    }
}
