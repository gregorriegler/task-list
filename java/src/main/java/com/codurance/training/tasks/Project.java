package com.codurance.training.tasks;

import java.util.List;

public class Project {
	
    private final String name;
    private final Tasks tasks;

    public Project(String name) {
		super();
		this.name = name;
		tasks = new Tasks();
	}

	public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
