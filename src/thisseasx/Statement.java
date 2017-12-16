package thisseasx;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        String username = user.isAdmin() ? "admin" : user.getUsername();

        sb.append("statement").append("_")
                .append(username).append("_")
                .append(date).append("_")
                .append(".txt");

        return sb.toString();
    }

    String getHistory() {
        StringBuilder sb = new StringBuilder();
        history.forEach(x -> sb.append(x).append("\n"));
        return sb.toString();
    }
}
