import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TodoService {
    private List<Todo> todos;
    private final String filename;
    private Stack<Todo> deleteTodos = new Stack<>();
    private void autoSave(){
        TodoManager.saveTodosToFile(todos, filename);
    }
    public TodoService(List<Todo> todos,String filename) {
        this.todos = todos;
        this.filename = filename;
    }
    //할일 추가
    public void add(String task,String category,String inputDate) {
        LocalDate dueDate;

        if (inputDate == null || inputDate.trim().isEmpty()) {
            dueDate = LocalDate.now();  // 기본값
        } else {
            try {
                dueDate = LocalDate.parse(inputDate);
            } catch (DateTimeParseException e) {
                System.out.println("⚠️ 마감일 형식이 잘못되었습니다. 오늘 날짜로 설정합니다.");
                dueDate = LocalDate.now();
            }
        }
        if (category == null || category.trim().isEmpty()) {
            category = "기타";
        }

        todos.add(new Todo(task, false, category, dueDate));
        autoSave();
        System.out.println("할 일이 추가되었습니다.");
    }
    // 할일 출력
    public void printAll() {
        if (todos.isEmpty()) {
            System.out.println("오늘 할 일이 없습니다.");
        } else {
            IntStream.range(0, todos.size())
                    .forEach(i -> System.out.println((i+1)+"."+todos.get(i)));
        }
    }
    // 할일 완료
    public void markDone(int id) {
        int realIndex = id - 1;
        if (isValidIndex(realIndex)) {
            todos.get(realIndex).markDone();
            autoSave();
            System.out.println("✅ 완료되었습니다.");
        } else {
            System.out.println("❌ 잘못된 번호입니다.");
        }
    }

    // 할일 삭제
    public void remove(int displayIndex) {
        int realIndex = displayIndex - 1;
        if (isValidIndex(realIndex)) {
            IntStream.range(0, todos.size())
                    .forEach(i -> System.out.println((i+1)+"."+todos.get(i)));
            deleteTodos.push(todos.get(realIndex));
            todos.remove(realIndex);
            autoSave();
            System.out.println("✅ 삭제되었습니다.");
        } else {
            System.out.println("❌ 잘못된 번호입니다.");
        }
    }
    //되돌리기 기능
    public void undoDelete(List<Todo>todos) {
            if(!deleteTodos.isEmpty()){
                Todo restored = deleteTodos.pop();
                todos.add(restored);
                autoSave();
                System.out.println("복구완료" + "\n"+ restored);
            }else{
                System.out.println("삭제한 할일이 없습니다");
            }

}
    public List<Todo> getTodos() {
        return todos;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < todos.size();
    }
    //테스트 코드
    public void removeStrict(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("삭제할 수 없는 인덱스입니다: " + index);
        }
        todos.remove(index);
    }
    // 할일 비었을때
    public boolean isEmpty() {
        return todos == null || todos.isEmpty();
    }
    // 할일 완료 안된것만 출력
    public void printIncomplete() {
        boolean hasIncomplete = false;
        List<Integer> incompleteTodos = IntStream.range(0, todos.size())
                .filter(i -> !todos.get(i).isDone())
                .boxed()
                .collect(Collectors.toList());
        if (incompleteTodos.isEmpty()) {
            System.out.println("✅ 완료할 일이 없습니다.");
        }else {
            incompleteTodos.forEach(i -> System.out.println((i + 1) + "." + todos.get(i)));
        }
    }
    // 검색 기능 추가
    public List<Todo> searchByKeyword(String keyword) {
        return todos.stream()
                .filter(todo -> todo.getTask().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
    //검색 결과 출력 메소드
    public void printSearchResult(String Keyword){
        List<Todo> result = searchByKeyword(Keyword);
        if (result.isEmpty()) {
            System.out.println("검색된 일이 없습니다");
        }else{
            result.forEach(System.out::println);
            }
        }
        public void printCategoryState(){
            Map<String, Long> stats = todos.stream()
                    .collect(Collectors.groupingBy(
                            Todo::getCategory,
                            Collectors.counting()
                    ));
            System.out.println("카테고리별 통계.");
            stats.forEach((category, count) ->
                    System.out.println("-"+category + ": " + count+"개"));
        }
}
