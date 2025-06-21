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
                    System.out.print("할 일을 입력하세요: ");
                    String task = sc.nextLine();
                    System.out.print("카테고리를 입력하세요: ");
                    String category = sc.nextLine();
                    System.out.print("마감일을 입력하세요 (yyyy-MM-dd) 또는 Enter: ");
                    String date = sc.nextLine();
                    service.add(task,category,date);
                    break;


                case 2:
                    System.out.println("오늘 할일 목록입니다.");
                    service.printAll();
                    break;

                case 3:
                    if (service.isEmpty()) {
                        System.out.println("❌ 할일이 없습니다.");
                    }else {
                        service.printIncomplete();
                        System.out.print("완료할 번호를 입력하세요.");
                        int choice2 = sc.nextInt();
                        sc.nextLine();
                        service.markDone(choice2);
                        break;
                    }
                case 4:
                    if (service.isEmpty()) {
                    System.out.println("❌ 삭제할 일이 없습니다.");
                    break;
                }else {
                        service.printAll();
                        System.out.print("삭제할 번호를 입력하세요.");
                        int choice3 = sc.nextInt();
                        sc.nextLine();
                        service.remove(choice3);
                        break;
                    }
                case 5:
                    if (service.isEmpty()) {
                        System.out.println("할일이 없습니다.");
                    }else {
                        System.out.print("검색할 키워드 입력: ");
                        String task1 = sc.nextLine();
                        service.printSearchResult(task1);
                    }break;
                case 6:
                    TodoManager.saveTodosToFile(todos, "todos.json");
                    System.out.println("종료합니다.");
                    return;
                case 7:
                    TodoManager.exportCsv(todos, "todos.csv");
                    break;
                    case 8:
                        service.undoDelete(todos);
                        break;

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
        System.out.println("5. 검색");
        System.out.println("6. 종료.");
        System.out.println("7.csv 내보내기");
        System.out.println("8.삭제취소");
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
