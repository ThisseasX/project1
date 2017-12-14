package thisseasx;

import com.sun.xml.internal.ws.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginScreen {

    private static String username;
    private static final Map<String, String> accounts = new HashMap<>();

    public static void main(String[] args) {
//        dbConnect();
//        input();

        //noinspection StatementWithEmptyBody
        while(!DatabaseConnector.login()){}
    }

    private static void dbConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/accounts?autoReconnect=true&useSSL=false";

        String username = "thiss";
        String password = "123";

        String sql = "select * from accounts";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet data = stmt.executeQuery(sql);

            while (data.next()) {

                accounts.put(data.getString("name"), data.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void input() {
        input(0);
        input(1);
    }

    private static void input(int i) {
        String[] questions = {"username", "password"};
        String message = String.format("Please enter your %s:", questions[i]);
        Scanner sc = new Scanner(System.in);

        boolean correctInput = false;
        do {
            System.out.println(message);
            switch (i) {
                case 0:
                    username = sc.nextLine().toLowerCase();
                    correctInput = accounts.containsKey(username);
                    break;
                case 1:
                    String password = sc.nextLine();
                    correctInput = accounts.get(username).equals(password);
            }
            if (!correctInput) System.out.printf("-- Wrong %s, try again --%n", questions[i]);
        } while (!correctInput);
        if (i == 1) System.out.printf("Welcome %s, how may I help you today?%n", StringUtils.capitalize(username));
    }
}
