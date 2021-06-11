package com.codurance.training.tasks;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

}
