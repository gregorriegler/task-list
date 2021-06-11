package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final Projects projects = new Projects();
    private final BufferedReader in;
    private final PrintWriter out;

    private long lastId = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                show();
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                check(commandRest[1]);
                break;
            case "uncheck":
                uncheck(commandRest[1]);
                break;
            case "help":
                help();
                break;
            case "deadline":
            	deadline(commandRest[1]);
            	break;
            default:
                error(command);
                break;
        }
    }

    private void deadline(String commandLine) {
    	
        String[] subcommandRest = commandLine.split(" ", 2);
        String taskId = subcommandRest[0];
    	String date = subcommandRest[1];
    	LocalDate deadline = LocalDate.parse(date);
    	Deadline newDeadline = new Deadline(deadline);
        Task task = projects.findTaskById(new TaskId(taskId));
        if(task!=null) {
            task.setDeadline(newDeadline);
        }
    }

    private void show() {
        for (Project project : projects) {
            out.println(project.getName());
            for (Task task : project.getTasks()) {
                out.printf("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }

    private void addProject(String name) {
        projects.add(new Project(name));
    }

    private void addTask(String projectName, String description) {
    	
    	Project project = projects.get(projectName);
        if (project == null) {
            out.printf("Could not find a project with the name \"%s\".", projectName);
            out.println();
            return;
        }
        Tasks projectTasks = project.getTasks();
        projectTasks.add(new Task(nextId(), description, false));
    }

    private void check(String idString) {
        setDone(idString, true);
    }

    private void uncheck(String idString) {
        setDone(idString, false);
    }

    private void setDone(String idString, boolean done) {
        TaskId id = new TaskId(idString);
        for (Project project : projects) {
            for (Task task : project.getTasks()) {
                if (task.getId().equals(id)) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

    private TaskId nextId() {
        return new TaskId(++lastId);
    }
}
