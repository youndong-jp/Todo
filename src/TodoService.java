// 일단 껍데기만 있는 TodoService 예시
public class TodoService {
    public String getAllTodos() {
        return "[{ \"id\": 1, \"task\": \"공부하기\", \"done\": false }]";
    }

    public String createTodo() {
        return "새로운 할 일이 생성되었습니다.";
    }

    public String getTodoById(int id) {
        return "ID " + id + "번 할 일 조회";
    }

    public String updateTodo(int id) {
        return "ID " + id + "번 할 일 수정 완료";
    }

    public String markDone(int id) {
        return "ID " + id + "번 할 일 완료 처리됨";
    }

    public String deleteTodo(int id) {
        return "ID " + id + "번 할 일 삭제됨";
    }
}
