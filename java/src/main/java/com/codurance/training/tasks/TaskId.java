package com.codurance.training.tasks;

public class TaskId {

    private final Long id;

    public TaskId(String id) {
        this.id = Long.parseLong(id);
    }

    public Long getId() {
        return id;
    }
}
