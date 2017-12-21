package thisseasx;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static thisseasx.ANSI.*;

class AccountManager {

    private final User user;
    private final List<User> otherUsers;

    AccountManager(User user) {
        this.user = user;
        this.otherUsers = new ArrayList<>(DBConnector.getOtherUsers(user));
    }

    private boolean hasZeroBalance(User source) {
        if (DBConnector.getBalance(source) <= 0) {
            String sourceAccount = source.getId() == 1 ?
                    StringUtils.capitalize(source.getUsername()) + 's' :
                    "Your";
            printColored(RED, String.format("--- %s account balance is 0 ---\n",
                    sourceAccount));
            return true;
        }
        return false;
    }

    private User requestUser(boolean deposit) {
        Scanner sc = new Scanner(System.in);
        String prompt = deposit ? "deposit to" : "withdraw from";
        int userIndex;
        while (true) {
            printColored(BLUE, String.format("Please choose a user to %s their account.", prompt));
            otherUsers.forEach(x -> printColored(CYAN, String.format(("%s) %s"), otherUsers.indexOf(x) + 1, x)));
            try {
                userIndex = sc.nextInt();
                if (userIndex > 0 && userIndex <= otherUsers.size()) break;
                printColored(RED, String.format("--- You must input a number between 1 and %s ---", otherUsers.size()));
            } catch (InputMismatchException e) {
                printColored(RED, "--- That's not a number ---");
                sc.nextLine();
            }
        }
        return otherUsers.get(userIndex - 1);
    }

    private int requestAmount() {
        Scanner sc = new Scanner(System.in);
        int amount;
        while (true) {
            printColored(BLUE, "Please choose an amount.");
            try {
                amount = sc.nextInt();
                if (amount > 0) break;
                printColored(RED, "--- You must input a positive amount ---");
            } catch (InputMismatchException e) {
                printColored(RED, "--- That's not a number ---");
                sc.nextLine();
            }
        }
        return amount;
    }

    void viewCoOpAccount() {
        int amount = DBConnector.getBalance(user);
        String transaction = "--- Requesting to view the Co-Operative's account balance ---\n\n" +
                String.format("The Co-operative's account balance is: €%s.", amount);
        finalizeTransaction(transaction);
    }

    private void finalizeTransaction(String transaction) {
        user.addTransaction(transaction);
        printColoredWithWarning(GREEN, BLUE, "\n" + transaction + "\n");
    }

    void viewMemberAccounts() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Requesting to view other members' account balance ---\n\n");
        for (User otherUser : otherUsers) {
            int amount = DBConnector.getBalance(otherUser);
            sb.append(String.format("%s's account balance is: €%s.",
                    StringUtils.capitalize(otherUser.getUsername()), amount));
            if (otherUsers.indexOf(otherUser) != otherUsers.size() - 1) sb.append("\n");
        }
        finalizeTransaction(sb.toString());
    }

    void viewOwnAccount() {
        int amount = DBConnector.getBalance(user);
        String transaction = "--- Requesting to view your account balance ---\n\n" +
                String.format("Your account balance is: €%s.", amount);
        finalizeTransaction(transaction);
    }

    void withdrawFromMember() {
        User source = requestUser(false);
        if (hasZeroBalance(source)) return;
        int amount;
        while (true) {
            amount = requestAmount();
            if (DBConnector.transfer(amount, source, user)) break;
            printColored(RED, "--- INSUFFICIENT BALANCE ---");
        }

        String transaction = "--- Requesting withdrawal ---\n\n" +
                String.format("Withdrew €%s from %s's account into the Co-operative's account.",
                        amount, source);

        finalizeTransaction(transaction);
    }

    void depositToMember() {
        if (hasZeroBalance(user)) return;
        User target = requestUser(true);
        int amount;
        while (true) {
            amount = requestAmount();
            if (DBConnector.transfer(amount, user, target)) break;
            printColored(RED, "--- INSUFFICIENT BALANCE ---");
        }

        String transaction = "--- Requesting deposit ---\n\n" +
                String.format("Deposited €%s to %s's account.",
                        amount, target);

        finalizeTransaction(transaction);
    }

    void depositToCoOp() {
        if (hasZeroBalance(user)) return;
        User target = new User();
        target.setId(1);
        int amount;
        while (true) {
            amount = requestAmount();
            if (DBConnector.transfer(amount, user, target)) break;
            printColored(RED, "--- INSUFFICIENT BALANCE ---");
        }

        String transaction = "--- Requesting deposit to the Co-operative's account---\n\n" +
                String.format("Deposited €%s to the Co-operative's account.", amount);

        finalizeTransaction(transaction);
    }

    void sendStatement() {
        user.addTransaction("### END OF TODAY'S STATEMENT ###");
        user.writeStatement();
    }
}
