package com.company.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoServicesTest {
    private TodoServices services;

    @BeforeEach
    void setUp() {
        services = new TodoServices();
    }

    @Test
    void addTodo_generatesIdAndStores() {
        Todo t = new Todo(null, "Buy milk");
        Todo added = services.addTodo(t);

        assertNotNull(added.getId(), "L'id doit être généré");
        assertEquals("Buy milk", added.getTitle());
        assertFalse(added.isCompleted());

        List<Todo> all = services.getAllTodos();
        assertEquals(1, all.size());
        assertEquals(added.getId(), all.get(0).getId());
    }

    @Test
    void addTodo_withProvidedId() {
        Todo t = new Todo(100L, "Task 100");
        Todo added = services.addTodo(t);
        assertEquals(100L, added.getId());

        List<Todo> all = services.getAllTodos();
        assertEquals(1, all.size());
    }

    @Test
    void addTodo_invalidTitle_null() {
        Todo t = new Todo(null, null);
        assertThrows(IllegalArgumentException.class, () -> services.addTodo(t));
    }

    @Test
    void addTodo_invalidTitle_empty() {
        Todo t = new Todo(null, "   ");
        assertThrows(IllegalArgumentException.class, () -> services.addTodo(t));
    }

    @Test
    void completeTodo_success() {
        Todo t = services.addTodo(new Todo(null, "Do homework"));
        Todo completed = services.completeTodo(t.getId());
        assertTrue(completed.isCompleted());

        // getAllTodos should reflect the change
        Todo fromList = services.getAllTodos().stream().filter(x -> x.getId().equals(t.getId())).findFirst().orElse(null);
        assertNotNull(fromList);
        assertTrue(fromList.isCompleted());
    }

    @Test
    void completeTodo_nonExistentId() {
        assertThrows(IllegalArgumentException.class, () -> services.completeTodo(999L));
    }

    @Test
    void completeTodo_nullId() {
        assertThrows(IllegalArgumentException.class, () -> services.completeTodo(null));
    }

    @Test
    void deleteTodo_success() {
        Todo a = services.addTodo(new Todo(null, "A"));
        Todo b = services.addTodo(new Todo(null, "B"));

        Todo removed = services.deleteTodo(a.getId());
        assertEquals(a.getId(), removed.getId());

        List<Todo> all = services.getAllTodos();
        assertEquals(1, all.size());
        assertEquals(b.getId(), all.get(0).getId());
    }

    @Test
    void deleteTodo_nonExistentId() {
        assertThrows(IllegalArgumentException.class, () -> services.deleteTodo(12345L));
    }

    @Test
    void deleteTodo_nullId() {
        assertThrows(IllegalArgumentException.class, () -> services.deleteTodo(null));
    }

    @Test
    void getAllTodos_returnsCopy() {
        services.addTodo(new Todo(null, "X"));
        List<Todo> all = services.getAllTodos();
        assertEquals(1, all.size());

        // modify returned list should not affect internal state
        all.clear();
        List<Todo> again = services.getAllTodos();
        assertEquals(1, again.size());
    }
}
