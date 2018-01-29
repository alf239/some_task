# Coding challenge

## The task

Your task is to develop a small application. We need you to build your application in your own GitHub repository.  Please do not fork our repository to create your project.  Once you are done, send us a link to your repository.

Please allow yourself at least 1 hour of uninterrupted time for this task, but feel free to spend as much time on the task as you like and make the solution and the code as perfect as you like.

We should be able to run your program with a single command. The program should print the answers to the questions and then exit. The answers should be in the following form:

```
  <question number>. <answer>
```

You will be submitting an application to be reviewed by a group of peers, this is a good chance to showcase your technical skills. Our expectation is that you will submit code that you feel best reflects your skills and that you would be happy to put into a production environment.

## The application

Your application needs to read the attached AddressBook file and answer the following questions:

1. How many males are in the address book?
2. Who is the oldest person in the address book?
3. How many days older is Bill than Paul?

Some insights into what we'll be looking for (and what we will not):

- Feel free to use any dependency management and build tools eg maven or gradle
- Please do not use database, we are more interested in your programming skills than your SQL skills
- Feel free to commit as often as you'd like. The more commits the better! Please commit at least once within the first hour
- It's better to complete 1 task than to start 3
- We will be looking at how you approach the task (e.g. how you break it into sub-tasks) and how you structure your code to answer the questions
- The code you write should be suitable for production
- We expect your code to be well tested and a TDD approach would be received well
- The code should be written with maintainability in mind

Good Luck!

# Solution

## The questions so far:

A few questions:

1. Should the language be Scala? It looks like a bit of an overkill for such a small task.
2. What do we know about the address book? Is it supposed to be correct? What encoding do we use? 
3. Do we expect the address book to change?
4. Can we assume that the output stream is correctly configured for whatever characters are there in the address book?
5. How this particular format handles commas in the field values?
6. Are the people in the book still alive? Can we be sure there are no people older than 99 years?
7. Can we assume that gender will always be specified and a “male” in the task refers to a person having “Male” (all Latin, capital M) in the second column?
8. Can we assume there’s only one Bill?  
9. Can we assume there’s only one Paul?  
10. What do we know about people’s birth places and the corresponding calendars?

## Basic Solution

```
10 PRINT "1. 3"
20 PRINT "2. Wes Jackson"
25 REM "Using https://www.timeanddate.com/date/durationresult.html?d1=15&m1=01&y1=1985&d2=16&m2=03&y2=1977"
30 PRINT "3. 2862" 
```

Immediately the question arises of the _format_ of the name output:
should it be `Wes Jackson` or `Wes Jackson, Male, 14/08/74`?

