package test;

import model.String_UI;
import model.TodoDAO;
import model.TodoDTO;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestMain {
    public static void main(String[] args) {
        String_UI ui = new String_UI();
        String date = "2024-11-06";
        TodoDTO dto = new TodoDTO();

        System.out.println(ui.menu());
        System.out.println(ui.selectToday());
        System.out.println(ui.selectDate( Date.valueOf(date) ) );

//        dto.setContent("오늘의 할일 Todolist : Test Case");
//        dto.setWriteAt(LocalDateTime.now());
//        dto.setTodoDate(LocalDate.now());

        TodoDAO dao = new TodoDAO();

//        dao.updateTodoContent(1,"Test Case Change");

        dao.deleteTodo(1);
    }
}
