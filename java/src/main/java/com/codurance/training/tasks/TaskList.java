package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final ProjectRepository projectRepository = new ProjectRepository();

    private final BufferedReader in;
    private final PrintWriter out;
    private final TaskIdGenerator taskIdGenerator;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out, new TaskIdGenerator()).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer, TaskIdGenerator taskIdGenerator) {
        this.in = reader;
        this.out = writer;
        this.taskIdGenerator = taskIdGenerator;
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
                check(new TaskId(commandRest[1]));
                break;
            case "uncheck":
                uncheck(new TaskId(commandRest[1]));
                break;
            case "help":
                help();
                break;
            case "deadline":
                deadline(new DeadLineArguments(commandRest));
                break;
            default:
                error(command);
                break;
        }
    }


    private void deadline(DeadLineArguments arguments) {
        TaskId taskId = arguments.getTaskId();
        for (Map.Entry<String, List<Task>> project : projectRepository.entrySet()) {
            for (Task task : project.getValue()) {
                if (Objects.equals(taskId, task.getId())) {
                    // TODO taskId#toString?
                    out.printf("Deadline added successfully to the task %s", taskId);
                    out.println();
                    return;
                }
            }
        }
        // TODO taskId#toString?
        out.printf("Task with the given id %s is not found.", taskId);
        out.println();
    }

    private void show() {
        out.print(this.projectRepository);
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
        projectRepository.put(name, new ArrayList<Task>());
    }

    private void addTask(String project, String description) {
        List<Task> projectTasks = projectRepository.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(this.taskIdGenerator.nextId(), description, false));
    }

    private void check(TaskId id) {
        setDone(id, true);
    }

    private void uncheck(TaskId id) {
        setDone(id, false);
    }

    private void setDone(TaskId id, boolean done) {
        for (Map.Entry<String, List<Task>> project : projectRepository.entrySet()) {
            for (Task task : project.getValue()) {
                if (Objects.equals(task.getId(), id)) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %s.", id.toString());
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

}
