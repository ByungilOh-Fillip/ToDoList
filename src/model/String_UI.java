package model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class String_UI {

    Date date = Date.valueOf(LocalDate.now());
    TodoDAO todoDao = new TodoDAO();
    TodoDTO dto = new TodoDTO();

    HashMap<Integer,String> menuHmap = new HashMap<>(){{
        put(1,"Daily ToDo");
        put(2,"Make ToDo");
        put(3,"Update ToDo");
        put(4,"Delete ToDo");
        put(5,"Send Message to Developer");
    }};

    public String menu(){

        String strMenu =  "==============================================";

        for(int i=1;i<=menuHmap.size();i++){
            strMenu += "\n"+i+".\t"+menuHmap.get(i);
        }

        strMenu += "\n==============================================\nEOF\n";

        return strMenu;
    }

    public String select2_4(){
        String str2_4 = "\n날짜를 입력해주세요. ex) 24.11.04\nEOF";
        return str2_4;
    }

    public String selectToday(){
        String result = "\nid\twrite_at\t\t\t\tdate\t\t\tcontent\n===========================================================================\n";
        List<TodoDTO> dtoList = todoDao.Daily_ToDo(date);

        for(TodoDTO dto : dtoList){
            result += dto.getId()+"\t";
            result += dto.getWriteAt()+"\t\t";
            result += dto.getTodoDate()+"\t\t";
            result += dto.getContent()+"\n";
        }

        return result;
    }

    public String selectDate(Date date){
        String result = "\nid\twrite_at\t\t\t\tdate\t\t\tcontent\n===========================================================================\n";
        List<TodoDTO> dtoList = todoDao.selectDate(date);

        for(TodoDTO dto : dtoList){
            result += dto.getId()+"\t";
            result += dto.getWriteAt()+"\t\t";
            result += dto.getTodoDate()+"\t\t";
            result += dto.getContent()+"\n";
        }

        return result;
    }

    public void insertTodo(String s){
        String[] resDateContent = s.split("/");
        dto.setWriteAt(LocalDateTime.now());
        dto.setTodoDate(LocalDate.parse(resDateContent[0]));
        dto.setContent(resDateContent[1]);
        todoDao.createTodo(dto);
        System.out.println("데이터 삽입 완료");
    }

}
