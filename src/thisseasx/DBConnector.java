package thisseasx;

import java.sql.*;
import java.util.Scanner;

import static thisseasx.ANSI.*;

class DBConnector {

    private String username;
    private boolean admin;

    boolean isAdmin() {
        return admin;
    }

    String getUsername() {
        return username;
    }

    boolean login() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/accounts?useSSL=false";

        String dbUsername = "thiss";
        String dbPassword = "123";

        username = input(0).toLowerCase();
        String password = input(1);

        String sql = "select _password, _admin from accounts where _name = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet data = ps.executeQuery();
            if (data.next() && data.getString("_password").equals(password)) {
                admin = data.getBoolean("_admin");
                printColored(GREEN, "--- Login Successful ---");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        printColored(RED, "--- Wrong username or password, please try again ---\n");
        return false;
    }

    private static String input(int i) {
        Scanner sc = new Scanner(System.in);
        String[] questions = {"username", "password"};
        String prompt = String.format("Please enter your %s:%n", questions[i]);
        printColored(BLUE, prompt);
        return sc.nextLine();
    }
}
