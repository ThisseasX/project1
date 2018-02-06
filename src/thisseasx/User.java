package thisseasx;

import com.sun.xml.internal.ws.util.StringUtils;

class User {

    private String username;
    private String password;
    private int id;

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

    int getId() {
        return id;
    }
    void setId(int id) {
        this.id = id;
    }

    void welcome() {
        String welcome = String.format("\nWelcome %s! How may I help you today?%n",
                StringUtils.capitalize(username));
        ANSI.printColored(ANSI.BLUE, welcome);
    }

    void bye() {
        String welcome = String.format("\nGoodbye %s! Have a great day!%n",
                StringUtils.capitalize(username));
        ANSI.printColored(ANSI.BLUE, welcome);
    }

    @Override
    public String toString() {
        return username;
    }
}
