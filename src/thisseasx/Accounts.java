package thisseasx;

import java.io.PrintWriter;

import static thisseasx.ANSI.CYAN;
import static thisseasx.ANSI.printColored;

class Accounts {

    static void viewCoOpAccount(User user) {
        printColored(CYAN,"viewCoOpAccount");
    }

    static void viewMemberAccounts(User user) {
        printColored(CYAN,"viewMemberAccounts");
    }

    static void viewOwnAccount(User user) {
        printColored(CYAN,"viewOwnAccount");
    }

    static void withdrawFromMember(User user) {
        printColored(CYAN,"withdrawFromMember");
    }

    static void depositToMember(User user) {
        printColored(CYAN,"depositToMember");
    }

    static void depositToCoOp(User user) {
        printColored(CYAN,"depositToCoOp");
    }

    static void sendStatement(User user) {
        printColored(CYAN,"sendStatement");
    }

    static void writeToFile(User user) {
//        PrintWriter pw = new PrintWriter();
    }
}
