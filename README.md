# Task List &nbsp; [![Build Status](https://travis-ci.org/codurance/task-list.png)](https://travis-ci.org/codurance/task-list)

This is an example of code obsessed with primitives.

A *primitive* is any concept technical in nature, and not relevant to your business domain. This includes integers, characters, strings, and collections (lists, sets, maps, etc.), but also things like threads, readers, writers, parsers, exceptions, and anything else purely focused on technical concerns. By contrast, the business concepts in this project, "task", "project", etc. should be considered part of your *domain model*. The domain model is the language of the business in which you operate, and using it in your code base helps you avoid speaking different languages, helping you to avoid misunderstandings. In our experience, misunderstandings are the biggest cause of bugs.

## Exercise

Try implementing the following features, refactoring primitives away as you go. Try not to implement any new behaviour until the code you're about to change has been completely refactored to remove primitives, i.e. **_Only refactor the code you're about to change, then make your change. Don't refactor unrelated code._**

One set of criteria to identify when primitives have been removed is to only allow primitives in constructor parameter lists, and as local variables and private fields. They shouldn't be passed into methods or returned from methods. The only exception is true infrastructure code—code that communicates with the terminal, the network, the database, etc. Infrastructure requires serialisation to primitives, but should be treated as a special case. You could even consider your infrastructure as a separate domain, technical in nature, in which primitives *are* the domain.

You should try to wrap tests around the behaviour you're refactoring. At the beginning, these will mostly be high-level system tests, but you should find yourself writing more unit tests as you proceed.

### Features

  1. Deadlines
      1. Give each task an optional deadline with the `deadline <ID> <date>` command.
      2. Show all tasks due today with the `today` command.
  2. Customisable IDs
      1. Allow the user to specify an identifier that's not a number.
      2. Disallow spaces and special characters from the ID.
  3. Deletion
      1. Allow users to delete tasks with the `delete <ID>` command.
  4. Views
      1. View tasks by date with the `view by date` command.
      2. View tasks by deadline with the `view by deadline` command.
      3. Don't remove the functionality that allows users to view tasks by project, but change the command to `view by project`.
