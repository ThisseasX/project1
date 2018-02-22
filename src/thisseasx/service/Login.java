package thisseasx.service;

import thisseasx.model.User;
import thisseasx.util.CLS;
import thisseasx.util.DBConnector;

import java.util.Scanner;

import static thisseasx.service.Login.UserInfo.PASSWORD;
import static thisseasx.service.Login.UserInfo.USERNAME;
import static thisseasx.util.ANSI.*;

public class Login {

    enum UserInfo {
        USERNAME,
        PASSWORD
    }

    public static User login() {
        User user = new User();
        while (true) if (loggedInSuccessfully(user)) break;
        CLS.cls();
        user.welcome();
        return user;
    }

    private static boolean loggedInSuccessfully(User user) {
        user.setUsername(input(USERNAME).toLowerCase());
        String password = input(PASSWORD);
        if (DBConnector.queryUser(user, password)) return true;
        CLS.cls();
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
