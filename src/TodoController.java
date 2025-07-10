import java.util.Scanner;

public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service){

        this.service = service;
    }

    private int extractId(String uri) {
        try {
            String[] parts = uri.split("/");
            return Integer.parseInt(parts[2]);
        } catch (Exception e) {
            return -1;
        }
    }

public String handleRequest(String method,String url){
        if(method.equals("GET")&&url.equals("/todos")){
           return service.getAllTodos();
        } else if(method.equals("POST")&&url.equals("/todos")){
            Scanner sc = new Scanner(System.in);
            System.out.println("할일 입력");
            String task = sc.nextLine();
            System.out.print("카테고리 입력: ");
            String category = sc.nextLine();
            System.out.print("마감일 입력 (yyyy-MM-dd): ");
            String inputDate = sc.nextLine();
            return service.createTodo(task,category,inputDate);
        } else if(method.equals("GET")&&url.startsWith("/todos/")){
            int id  = extractId(url);
            return service.getTodoById(id);
        } else if(method.equals("PUT")&&url.startsWith("/todos/")){
            int id = extractId(url);
            Scanner sc = new Scanner(System.in);
            System.out.println("변경된 일 입력");
            String task = sc.nextLine();
            System.out.print("카테고리 입력: ");
            String category = sc.nextLine();
            String inputDate = sc.nextLine();
            System.out.print("마감일 입력 (yyyy-MM-dd): ");
            return service.updateTodo(id,task,category,inputDate);
        } else if(method.equals("PATCH")&&url.startsWith("/todos/")&&url.endsWith("/done")){
            int id = extractId(url);
            return service.markDone(id);
        } else if(method.equals("DELETE")&&url.startsWith("/todos/")){
            int id = extractId(url);
            return service.deleteTodo(id);
        }

        return "404 Not Found";
}
}