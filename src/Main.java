import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TodoRepository repo = new MemoryTodoRepository();
        TodoService service = new TodoService(repo);
        TodoController controller = new TodoController(service);

        System.out.println(" 무슨 일을 하실건가요? 종료 -> exit");

        while (true) {
            System.out.print("\n>> 입력");
            String line = sc.nextLine().trim();

            if (line.equals("exit")) {
                break;
            }
            String[] parts = line.split(" ");
            if (parts.length != 2) {
                System.out.println("X 형식 Method/uri");
                continue;
            }
            String method = parts[0];
            String uri = parts[1];

            String response = controller.handleRequest(method, uri);
            System.out.println("📦 응답: " + response);
        }
    }
}
