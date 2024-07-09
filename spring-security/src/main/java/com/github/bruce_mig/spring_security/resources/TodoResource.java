package com.github.bruce_mig.spring_security.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {

    private Logger log = LoggerFactory.getLogger(getClass());

    private static final List<Todo> TODO_LIST =
            List.of(new Todo("name", "Learn AWS"),
                    new Todo("name", "Get AWS certified"));

    @GetMapping("/todos")
    public List<Todo> retrieveAllTodos(){
        return TODO_LIST;
    }

    @GetMapping("/users/{username}/todos")
    public Todo retrieveTodosByUsername(@PathVariable String username){
        return TODO_LIST.getFirst();
    }

    @PostMapping("/users/{username}/todos")
    public void createTodoForUser(@PathVariable String username, @RequestBody Todo todo){
        log.info("Create {} for {}", todo, username);
    }
}


record Todo (String username, String description){}
