package com.mycompany.part3;
public class Login {
    private String userName;
    private String userPassword;
    private String userFirstName;
    private String userLastName;

    public Login(String userName, String userPassword, String userFirstName, String userLastName) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    public boolean checkUserName() {
        boolean condition = userName.contains("_") && userName.length() <= 5;
        return condition;
    }

    public boolean checkPasswordComplexity() {
        if (userPassword.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;

        for (char c : userPassword.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
            if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }

        return hasUppercase && hasNumber && hasSpecialChar;
    }

    public String registerUser() {
        String message;

        if (checkUserName()) {
            System.out.println("Username successfully captured");

            if (checkPasswordComplexity()) {
                System.out.println("Password successfully captured");
                message = "The user has been registered successfully";
            } else {
                message = "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.";
            }
        } else {
            message = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }

        return message;
    }

    public boolean loginUser(String userName, String userPassword) {
        if (this.userName.equals(userName) && this.userPassword.equals(userPassword)) {
            return true;
        } else {
            return false;
        }
    }

    public String returnLoginStatus(String userName, String userPassword) {
        if (loginUser(userName, userPassword)) {
            return "Welcome " + userFirstName + " " + userLastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
