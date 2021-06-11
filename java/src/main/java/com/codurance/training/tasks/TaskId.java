package com.codurance.training.tasks;

public class TaskId {

	private final long id;

	public TaskId(long id) {
		super();
		this.id = id;
	}

	public TaskId(String id) {
		this(Long.parseLong(id));
	}


	
}