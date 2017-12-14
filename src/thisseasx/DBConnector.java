package thisseasx;

import com.sun.xml.internal.ws.util.StringUtils;

import java.sql.*;
import java.util.Scanner;

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

        String sql = "select _password, _access from accounts where _name = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet data = ps.executeQuery();
            if (data.next() && data.getString("_password").equals(password)) {
                admin = data.getInt("_access") > 4;
                System.out.printf("Welcome %s! How may I help you today?%n", StringUtils.capitalize(username));
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("--- Wrong username or password, please try again ---");
        return false;
    }

    private static String input(int i) {
        Scanner sc = new Scanner(System.in);
        String[] questions = {"username", "password"};
        System.out.printf("Please enter your %s:%n", questions[i]);
        return sc.nextLine();
    }
}
