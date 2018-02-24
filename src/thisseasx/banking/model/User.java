package thisseasx.banking.model;

import thisseasx.banking.util.ANSI;
import thisseasx.banking.util.StringUtils;

public class User {

    private String username;
    private int id;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void welcome() {
        String welcome = String.format("\nWelcome %s! How may I help you today?%n",
                StringUtils.capitalize(username));
        ANSI.printColored(ANSI.BLUE, welcome);
    }
    public void bye() {
        String welcome = String.format("\nGoodbye %s! Have a great day!%n",
                StringUtils.capitalize(username));
        ANSI.printColored(ANSI.BLUE, welcome);
    }

    @Override
    public String toString() {
        return username;
    }
}
