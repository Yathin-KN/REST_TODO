package com.example.core;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@NamedQuery(
        name = "Todo.getAllTodo",
        query = "SELECT t from Todo t"
)
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description" , nullable = false)
    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "start date cannot be null")
    @Column(name = "start_date" , nullable = false)
    private LocalDate startDate;

    @NotNull(message = "end date cannot be null")
    @Column(name = "end_date" , nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status" , nullable = false)
    private TaskStatus status = TaskStatus.TODO;

    public enum TaskStatus{
        TODO,
        WIP,
        DONE
    }

    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

}
