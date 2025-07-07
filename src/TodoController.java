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
            return service.createTodo();
        } else if(method.equals("GET")&&url.startsWith("/todos/")){
            int id  = extractId(url);
            return service.getTodoById(id);
        } else if(method.equals("PUT")&&url.startsWith("/todos/")){
            int id = extractId(url);
            return service.updateTodo(id);
        } else if(method.equals("PATCH")&&url.startsWith("/todos/")){
            int id = extractId(url);
            return service.markDone(id);
        } else if(method.equals("DELETE")&&url.startsWith("/todos/")){
            int id = extractId(url);
            return service.deleteTodo();
        }

        return "404 Not Found";
}
}