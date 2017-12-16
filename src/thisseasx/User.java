package thisseasx;

import com.sun.xml.internal.ws.util.StringUtils;

class User {

    private String username;
    private String password;
    private boolean admin;

    private Statement statement;

    String getUsername() {
        return username;
    }
    void setUsername(String username) {
        this.username = username;
    }

    String getPassword() {
        return password;
    }
    void setPassword(String password) {
        this.password = password;
    }

    boolean isAdmin() {
        return admin;
    }
    void setAdmin(boolean admin) {
        this.admin = admin;
    }

    void welcome() {
        String accessLevel = admin ? "admin" : "user";
        String welcome = String.format("Welcome %s %s! How may I help you today?%n",
                accessLevel, StringUtils.capitalize(username));
        ANSI.printColored(ANSI.BLUE, welcome);
    }

    void bye() {
        String accessLevel = admin ? "admin" : "user";
        String welcome = String.format("Goodbye %s %s! Please come again!%n",
                accessLevel, StringUtils.capitalize(username));
        ANSI.printColored(ANSI.BLUE, welcome);
    }

    @Override
    public String toString() {
        return username;
    }
}
