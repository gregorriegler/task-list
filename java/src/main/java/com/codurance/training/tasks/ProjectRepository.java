package com.codurance.training.tasks;

import java.util.*;

public class ProjectRepository extends LinkedHashMap<String, List<Task>> {

    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        for (Map.Entry<String, List<Task>> project : this.entrySet()) {
            resultStringBuilder.append(project.getKey()).append(System.lineSeparator());
            for (Task task : project.getValue()) {
                resultStringBuilder.append(task.getShowMsg());
            }
            resultStringBuilder.append(System.lineSeparator());
        }
        return resultStringBuilder.toString();
    }


    void addProject(String name) {
        put(name, new ArrayList<Task>());
    }

    public void addTask(String projectName, Task task) {
        List<Task> projectTasks = this.get(projectName);
        projectTasks.add(task);
    }

    boolean missing(String key) {
        return get(key) == null;
    }

    void foo(TaskId id, boolean done, TaskList taskList) {
        for (Map.Entry<String, List<Task>> project : entrySet()) {
            for (Task task : project.getValue()) {
                if (Objects.equals(task.getId(), id)) {
                    task.setDone(done);
                    return ;
                }
            }
        }
       
    }
}
