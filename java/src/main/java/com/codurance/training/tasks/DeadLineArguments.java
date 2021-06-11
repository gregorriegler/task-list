package com.codurance.training.tasks;

import java.time.LocalDate;

public class DeadLineArguments {

    private final TaskId taskId;

    private final LocalDate date;

    public DeadLineArguments(TaskId taskId, LocalDate date) {
        this.taskId = taskId;
        this.date = date;
    }

    public DeadLineArguments() {

    }

    public TaskId getTaskId() {
        return taskId;
    }

    public LocalDate getDate() {
        return date;
    }

}
