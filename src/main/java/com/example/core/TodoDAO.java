package com.example.core;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TodoDAO extends AbstractDAO<Todo> {
    public TodoDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public Todo create(Todo todo){
        return persist(todo);
    }

    public Optional<Todo> findById(long id){
        return Optional.ofNullable(get(id));
    }

    public List<Todo> getAllTodo(){
        @SuppressWarnings("unchecked")
        Query<Todo> query = (Query<Todo>) namedQuery("Todo.getAllTodo");
        return list(query);
    }

    public void delete(Long id){
        Optional<Todo> todo = findById(id);
        todo.ifPresent(t -> {
            currentSession().remove(t);
        });
    }

    public Todo update(Long id, String description, LocalDate startDate , LocalDate endDate , Todo.TaskStatus status){
        Optional<Todo> existingTodo = findById(id);
        existingTodo.ifPresent(todo->{
            if(description!=null){todo.setDescription(description);}
            if(startDate!=null){todo.setStartDate(startDate);}
            if(endDate!=null){todo.setEndDate(endDate);}
            if(status!=null){todo.setStatus(status);}
            persist(todo);
        });
        return existingTodo.orElseThrow(()-> new RuntimeException("Task with id " + id + "does not exist"));
    }

}
