import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Todo> todos = TodoManager.loadTodosFromFile("todos.json");
        TodoService service = new TodoService(todos);

        while (true) {
            printMenu();
            int choice = readInt(sc);

            switch (choice) {
                case 1:
                    System.out.print("할일을 입력하세요 : ");
                    String task = sc.nextLine();
                    service.add(task);
                    break;

                case 2:
                    System.out.println("오늘 할일 목록입니다.");
                    service.printAll();
                    break;

                case 3:
                    System.out.println("완료할 번호를 입력하세요.");
                    int choice2 = sc.nextInt();
                    sc.nextLine();
                    service.markDone(choice2);
                    break;

                case 4:
                    System.out.println("삭제할 번호를 입력하세요.");
                    int choice3 = sc.nextInt();
                    sc.nextLine();
                    service.remove(choice3);
                    break;

                case 5:
                    TodoManager.saveTodosToFile(todos, "todos.json");
                    System.out.println("종료합니다.");
                    return;

                default:
                    System.out.println("잘못된 입력입니다.");
            }

        }
    }

    private static void printMenu() {
        System.out.println("\n📋 메뉴를 선택해 주세요");
        System.out.println("1. 할 일 추가");
        System.out.println("2. 현재 할 일 확인");
        System.out.println("3. 할 일 완료 처리");
        System.out.println("4. 할 일 삭제");
        System.out.println("5. 종료");
        System.out.print("번호를 입력해 주세요: ");
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("숫자를 입력해 주세요!");
            sc.next(); // 잘못된 입력 제거
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }
}
