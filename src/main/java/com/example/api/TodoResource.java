package com.example.api;

import com.example.core.Todo;
import com.example.core.TodoDAO;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    private final TodoDAO todoDAO;

    public TodoResource(TodoDAO todoDAO){
        this.todoDAO = todoDAO;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Todo getTodoById(@PathParam("id") Long id){
        return todoDAO.findById(id).orElseThrow(() -> new NotFoundException("Todo with ID " + id + " not found"));
    }

    @GET
    @Path("/getAllTodos")
    @UnitOfWork
    public List<Todo> getAllTodos(){
        return todoDAO.getAllTodo();
    }

    @POST
    @Path("/create")
    @UnitOfWork
    public Response createTodo(@Valid Todo todo){
        Todo createdTodo=todoDAO.create(todo);
        return Response.status(Response.Status.CREATED)
                .entity(createdTodo)
                .build();
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Response updateTodo(@PathParam("id") long id,@Valid Todo updatedTodo){
        updatedTodo.setId(id);

        Todo updatedTodoEntity = todoDAO.update(id,updatedTodo.getDescription(),updatedTodo.getStartDate(),updatedTodo.getEndDate(),updatedTodo.getStatus());
        return Response.ok(updatedTodoEntity).build();
    }

}
