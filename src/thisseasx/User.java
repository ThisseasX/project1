package thisseasx;

import com.sun.xml.internal.ws.util.StringUtils;

class User {

    private String username;
    private String password;
    private int id;
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

    int getId() {
        return id;
    }
    void setId(int id) {
        this.id = id;
    }

    void setStatement(Statement statement) {
        this.statement = statement;
    }

    String getStatementFileName() {
        return statement.getFileName();
    }
    void addTransaction(String transactionName) {
        statement.addTransaction(transactionName);
    }
    void writeStatement(){
        statement.writeStatement();
    }


    void welcome() {
        String welcome = String.format("Welcome %s! How may I help you today?%n",
                StringUtils.capitalize(username));
        ANSI.printColored(ANSI.BLUE, welcome);
    }

    void bye() {
        String welcome = String.format("Goodbye %s! Have a great day!%n",
                StringUtils.capitalize(username));
        ANSI.printColored(ANSI.BLUE, welcome);
    }

    @Override
    public String toString() {
        return username;
    }
}
