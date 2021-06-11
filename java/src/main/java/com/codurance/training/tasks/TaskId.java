package com.codurance.training.tasks;

public class TaskId {

    private final Long id;

    public TaskId(Long id) {
        this.id = id;
    }

    public TaskId(String id) {
        this.id = Long.parseLong(id);
    }

    public TaskId next() {
        return new TaskId(id + 1);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
