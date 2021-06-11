package com.codurance.training.tasks;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskId)) return false;
        TaskId taskId = (TaskId) o;
        return Objects.equals(id, taskId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
