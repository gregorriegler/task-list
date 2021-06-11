package com.codurance.training.tasks;

import java.time.LocalDate;

public class Deadline {
    
    private final LocalDate date;

	public Deadline(LocalDate date) {
		super();
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}
}
