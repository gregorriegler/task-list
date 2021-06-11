package com.codurance.training.tasks;

public class TaskId {

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		TaskId taskId = (TaskId) o;

		return taskId.id == (id);
	}

	private final long id;

	public TaskId(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public TaskId(String id) {
		this(Long.parseLong(id));
	}


	
}