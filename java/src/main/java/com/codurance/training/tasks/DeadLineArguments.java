package com.codurance.training.tasks;

import jdk.vm.ci.meta.Local;

import java.time.LocalDate;

public class DeadLineArguments {

    private final TaskId taskId;
    private final LocalDate date;

    public DeadLineArguments(TaskId taskId, LocalDate date) {
        this.taskId = taskId;
        this.date = date;
    }

    public DeadLineArguments(String[] arguments) {
        this(new TaskId(arguments[1]), LocalDate.parse(arguments[2]));
    }

    public TaskId getTaskId() {
        return taskId;
    }

    public LocalDate getDate() {
        return date;
    }

}
