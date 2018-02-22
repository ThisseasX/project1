package thisseasx.service;

import thisseasx.model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static thisseasx.util.ANSI.MAGENTA;
import static thisseasx.util.ANSI.printColored;

class Statement {

    private static final List<String> history = new ArrayList<>();

    static void addTransaction(String transactionName) {
        history.add(transactionName);
    }

    private static String getFileName(User user) {
        StringBuilder sb = new StringBuilder();

        String date = new SimpleDateFormat("dd_MM_yyyy").format(Calendar.getInstance().getTime());
        String username = user.getId() == 1 ? "admin" : user.getUsername();

        sb.append("statement").append("_")
                .append(username).append("_")
                .append(date).append(".txt");

        return sb.toString();
    }

    private static String getHistory() {
        StringBuilder sb = new StringBuilder();
        sb.append("### ").append(Calendar.getInstance().getTime()).append(" ###").append("\n\n");
        history.forEach(x -> sb.append(x).append("\n\n"));
        return sb.toString();
    }

    static void writeStatement(User user) {
        printColored(MAGENTA, "### TODAY'S STATEMENT ###\n");
        printColored(MAGENTA, getHistory());
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(getFileName(user), true));
            pw.append(getHistory()).append("\n");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
