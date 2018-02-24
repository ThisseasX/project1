package thisseasx.banking.service;

import thisseasx.banking.model.User;
import thisseasx.banking.util.CLS;
import thisseasx.banking.util.DBConnector;

import java.util.Scanner;

import static thisseasx.banking.service.Login.UserInfo.PASSWORD;
import static thisseasx.banking.service.Login.UserInfo.USERNAME;
import static thisseasx.banking.util.ANSI.*;

public class Login {

    enum UserInfo {
        USERNAME,
        PASSWORD
    }

    public static User login() {
        User user = new User();
        while (true) if (loggedInSuccessfully(user)) break;
        user.welcome();
        return user;
    }

    private static boolean loggedInSuccessfully(User user) {
        user.setUsername(input(USERNAME).toLowerCase());
        String password = input(PASSWORD);
        if (DBConnector.queryUser(user, password)) return true;
        printColored(RED, "--- Wrong username or password, please try again ---");
        return false;
    }

    private static String input(UserInfo userInfo) {
        Scanner sc = new Scanner(System.in);
        String question = userInfo.name().toLowerCase();
        String prompt = String.format("Please enter your %s:", question);
        printColored(BLUE, prompt);
        return sc.nextLine();
    }
}
