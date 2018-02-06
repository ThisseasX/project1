package thisseasx;

import java.util.Scanner;

import static thisseasx.ANSI.*;
import static thisseasx.Login.UserInfo.PASSWORD;
import static thisseasx.Login.UserInfo.USERNAME;

class Login {

    enum UserInfo {
        USERNAME,
        PASSWORD
    }

    static User login() {
        User user = new User();
        while (true) if (loggedInSuccessfully(user)) break;
        user.welcome();
        return user;
    }

    private static boolean loggedInSuccessfully(User user) {
        user.setUsername(input(USERNAME).toLowerCase());
        user.setPassword(input(PASSWORD));
        if (DBConnector.queryUser(user)) return true;
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
