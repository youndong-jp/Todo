import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TodoManager {
    public static void saveTodosToFile(ArrayList<Todo> todos, String filename) {
        Gson gson = new Gson();
        String json = gson.toJson(todos);

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(json);
            System.out.println("✅ 저장 완료: " + filename);
        } catch (IOException e) {
            System.out.println("❌ 저장 실패: " + e.getMessage());
        }
    }
    public static ArrayList<Todo> loadTodosFromFile(String filename) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            Type type = new TypeToken<ArrayList<Todo>>() {}.getType();
            ArrayList<Todo> todos = gson.fromJson(reader, type);
            System.out.println("✅ 불러오기 완료");
            return todos;
        } catch (IOException e) {
            System.out.println("❌ 불러오기 실패: " + e.getMessage());
            return new ArrayList<>(); // 실패 시 빈 리스트 반환
        }
    }
}
