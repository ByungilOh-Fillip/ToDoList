package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TodoDTO {
    private Integer id;
    private LocalDateTime writeAt;
    private LocalDate todoDate;
    private String content;

    public TodoDTO() {
    }

    public TodoDTO(Integer id,LocalDateTime writeAt, LocalDate todoDate, String content) {
        this.id = id;
        this.writeAt = writeAt;
        this.todoDate = todoDate;
        this.content = content;
    }

    // Getter와 Setter 메서드
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getWriteAt() {
        return writeAt;
    }

    public void setWriteAt(LocalDateTime writeAt) {
        this.writeAt = writeAt;
    }

    public LocalDate getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(LocalDate todoDate) {
        this.todoDate = todoDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


