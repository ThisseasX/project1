package thisseasx;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static thisseasx.ANSI.CYAN;
import static thisseasx.ANSI.MAGENTA;
import static thisseasx.ANSI.printColored;

class Statement {

    private final User user;
    private List<String> history = new ArrayList<>();

    Statement(User user) {
        this.user = user;
    }

    void addTransaction(String transactionName) {
        history.add(transactionName);
    }

    String getFileName() {
        StringBuilder sb = new StringBuilder();

        String date = new SimpleDateFormat("dd_MM_yyyy").format(Calendar.getInstance().getTime());
        String username = user.getUsername();

        sb.append("statement").append("_")
                .append(username).append("_")
                .append(date).append(".txt");

        return sb.toString();
    }

    private String getHistory() {
        StringBuilder sb = new StringBuilder();
        sb.append(Calendar.getInstance().getTime()).append("\n\n");
        history.forEach(x -> sb.append(x).append("\n\n"));
        return sb.toString();
    }

    void writeStatement() {
        printColored(MAGENTA, "### TODAY'S STATEMENT ###\n");
        printColored(MAGENTA, getHistory());
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(user.getStatementFileName(), true));
            pw.append(getHistory()).append("\n");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
