package com.codurance.training.tasks;

public class TaskIdGenerator {

    private TaskId lastId;

    TaskIdGenerator() {
        lastId = new TaskId(0L);
    }

    public TaskId nextId() {
        this.lastId = lastId.next();
        return lastId;
    }
}
