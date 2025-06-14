import java.time.LocalDate;
import java.util.List;

public class TodoService {
    private List<Todo> todos;

    public TodoService(List<Todo> todos) {
        this.todos = todos;
    }

    public void add(String task){
        todos.add(new Todo(task, false, LocalDate.now()));
        System.out.println("할 일이 추가되었습니다.");
    }
    public void printAll(){
        if(todos.isEmpty()) {
            System.out.println("오늘 할 일이 없습니다.");
        }else{
            for (int i=0; i<todos.size(); i++) {
                System.out.println((i+1)+"."+todos.get(i));
            }
        }
    }

    public void markDone(int id){
        if(isValidIndex(id)){
            todos.get(id).markDone();
            System.out.println("✅ 완료되었습니다.");
        }else{
            System.out.println("❌ 잘못된 번호입니다.");
        }
    }
    public void remove(int id){
        if (isValidIndex(id)) {
            todos.remove(id);
            System.out.println("삭제되었습니다.");
        } else {
            System.out.println("❌ 잘못된 번호입니다.");
        }
    }

    public List<Todo> getTodos(){
        return todos;
    }

    private boolean isValidIndex(int index){
        return index >= 0 && index < todos.size();
    }
    public void removeStrict(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("삭제할 수 없는 인덱스입니다: " + index);
        }
        todos.remove(index);
    }

}
