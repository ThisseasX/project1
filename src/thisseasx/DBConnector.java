package thisseasx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static thisseasx.ANSI.GREEN;
import static thisseasx.ANSI.printColored;

class DBConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/accounts?useSSL=false";
    private static final String DB_USERNAME = "thiss";
    private static final String DB_PASSWORD = "123";

    static boolean query(User user) {
        initializeJDBC();
        String sql = "select _password, _admin from accounts where _name = ?";
        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ResultSet data = ps.executeQuery();
            if (data.next() && data.getString("_password").equals(user.getPassword())) {
                user.setAdmin(data.getBoolean("_admin"));
                printColored(GREEN, "--- Login Successful ---");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    static List<User> getOtherUsers(User user) {
        List<User> users = new ArrayList<>();
        String sql = "select _name from accounts where _name != ? AND _admin = ?";
        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setBoolean(2, false);
            ResultSet data = ps.executeQuery();
            while (data.next()) {
                User otherUser = new User();
                otherUser.setUsername(data.getString("_name"));
                otherUser.setPassword("unknown_password");
                otherUser.setAdmin(false);
                users.add(otherUser);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    private static void initializeJDBC() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
