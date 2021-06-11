package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;

public class Projects extends ArrayList<Project> {

    public Project get(String name) {
    	for (Project project : this) {
    		if (project.getName().equals(name)) {
    			return project;
    		}
    	}
        return null;
    }
}
