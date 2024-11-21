package com.mycompany.part3;

import com.mycompany.part3.Login;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Part3 {

    private static ArrayList<Task> tasks = new ArrayList<>();
 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (loginUser(scanner)) {
            JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
            runMenu();
        }
    }

    private static void runMenu() {
        String[] options = {"1. Add Tasks", "2. Show Report", "3. Search Task", "4. Delete Task", "5. Quit"};
        boolean running = true;

        while (running) {
            String choice = (String) JOptionPane.showInputDialog(null, "Choose an option:", "Menu",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (choice.equals("1. Add Tasks")) {
                addTasks();
            } else if (choice.equals("2. Show Report")) {
                displayTaskReport();
            } else if (choice.equals("3. Search Task")) {
                searchTask();
            } else if (choice.equals("4. Delete Task")) {
                deleteTask();
            } else if (choice.equals("5. Quit")) {
                running = false;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid choice. Try again.");
            }
        }
    }

    private static boolean loginUser(Scanner scanner) {
        
        System.out.println("Register a new account:");
        System.out.println("Enter username: ");
        String userName = scanner.nextLine();

        System.out.print("Enter password: ");
        String userPassword = scanner.nextLine();

        System.out.print("Enter first name: ");
        String userFirstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String userLastName = scanner.nextLine();
        
        Login login = new Login(userName, userPassword, userFirstName, userLastName);
        String registrationResult = login.registerUser();
        System.out.println(registrationResult);
        Boolean loginStatus = false; 
    
        if (registrationResult.equals("The user has been registered successfully")) {
            System.out.println("\nLogin to your account:");
            System.out.print("Enter username: ");
            String loginUserName = scanner.nextLine();

            System.out.print("Enter password: ");
            String loginUserPassword = scanner.nextLine();

            String loginResponse = login.returnLoginStatus(loginUserName, loginUserPassword);
            System.out.println(loginResponse);
             
            loginStatus = login.loginUser(userName, userPassword);
        }
        return loginStatus;
    }

    private static void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("How many tasks do you want to add?"));

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter task name:");
            String taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");

            if (taskDescription.length() > 50) {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
                taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");
            }

            String developerName = JOptionPane.showInputDialog("Enter developer name:");
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration (in hours):"));
            String[] statusOptions = {"To Do", "Doing", "Done"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, "Select task status:", "Task Status",
                    JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

            Task task = new Task(taskName, taskDescription, developerName, taskDuration, taskStatus, tasks.size());

            tasks.add(task);

            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }

        JOptionPane.showMessageDialog(null, "Total task duration across all tasks: " + Task.returnTotalHours() + " hours");
    }

    private static void searchTask() {
        String taskName = JOptionPane.showInputDialog("Enter task name to search:");
        boolean found = false;

    
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getTaskName().equalsIgnoreCase(taskName)) {
                JOptionPane.showMessageDialog(null, "Task Found:\n" + task.printTaskDetails());
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }

    private static void deleteTask() {
        String taskName = JOptionPane.showInputDialog("Enter task name to delete:");
        boolean found = false;

    
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskName().equalsIgnoreCase(taskName)) {
                tasks.remove(i);  
                JOptionPane.showMessageDialog(null, "Task " + taskName + " deleted.");
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }

    private static void displayTaskReport() {
        StringBuilder report = new StringBuilder("Task Report:\n");

       
        for (int i = 0; i < tasks.size(); i++) {
            report.append(tasks.get(i).printTaskDetails()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, report.toString());
    }
}
