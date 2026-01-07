package com.company.todo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TodoServices {

    private final List<Todo> todos = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(0);


    public Todo addTodo(Todo todo) {
        if (todo == null) {
            throw new IllegalArgumentException("Todo ne peut pas être null");
        }
        String title = todo.getTitle();
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être null ou vide");
        }

        if (todo.getId() == null) {
            todo.setId(idCounter.incrementAndGet());
        } else if (findById(todo.getId()) != null) {
            throw new IllegalArgumentException("ID déjà existant: " + todo.getId());
        }

        todos.add(todo);
        return todo;
    }


    public List<Todo> getAllTodos() {
        return new ArrayList<>(todos);
    }


    public Todo completeTodo(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID ne peut pas être null");
        }
        Todo t = findById(id);
        if (t == null) {
            throw new IllegalArgumentException("ID inexistant: " + id);
        }
        t.setCompleted(true);
        return t;
    }

    public Todo deleteTodo(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID ne peut pas être null");
        }
        Iterator<Todo> it = todos.iterator();
        while (it.hasNext()) {
            Todo t = it.next();
            if (id.equals(t.getId())) {
                it.remove();
                return t;
            }
        }
        throw new IllegalArgumentException("ID inexistant: " + id);
    }

    private Todo findById(Long id) {
        for (Todo t : todos) {
            if (id.equals(t.getId())) {
                return t;
            }
        }
        return null;
    }
}
