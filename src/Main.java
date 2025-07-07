import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TodoService service = new TodoService();
        TodoController controller = new TodoController(service);

        System.out.println(" 무슨 일을 하실건가요?");
        System.out.println("ex): POST /todos");

        while (true) {
            System.out.print("\n>> ");
            String method = sc.next();
            String uri = sc.nextLine().trim();

            String response = controller.handleRequest(method, uri);
            System.out.println("📦 응답: " + response);
        }
    }
}
