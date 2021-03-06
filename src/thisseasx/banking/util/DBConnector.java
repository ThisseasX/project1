package thisseasx.banking.util;

import thisseasx.banking.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static thisseasx.banking.util.ANSI.GREEN;
import static thisseasx.banking.util.ANSI.printColored;

public class DBConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/afdemp_java_1?useSSL=false";
    private static final String DB_USERNAME = "thiss";
    private static final String DB_PASSWORD = "123";

    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String TABLE_USERS = "users";

    private static final String COL_ID = "id";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";

    private static final String COL_USER_ID = "user_id";
    private static final String COL_AMOUNT = "amount";

    private static final String SQL_UPDATE = "" +
            "UPDATE accounts " +
            "SET amount = ?, transaction_date = ? " +
            "WHERE user_id = ?";

    public static boolean queryUser(User user, String password) {
        initializeJDBC();

        String[] columns = {COL_ID};
        String[] selection = {COL_USERNAME + "=?", "BINARY " + COL_PASSWORD + "=?"};
        String sql = sqlQueryBuilder(TABLE_USERS, columns, selection);

        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, password);
            ResultSet data = ps.executeQuery();

            if (data.next()) {
                user.setId(data.getInt("id"));
                printColored(GREEN, "--- Login Successful ---");
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static List<User> getOtherUsers(User user) {
        List<User> users = new ArrayList<>();

        String[] columns = {COL_USERNAME, COL_ID};
        String[] selection = {COL_USERNAME + "!=?", COL_ID + ">?"};
        String sql = sqlQueryBuilder(TABLE_USERS, columns, selection);

        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setInt(2, 1);
            ResultSet data = ps.executeQuery();

            while (data.next()) {
                User otherUser = new User();
                otherUser.setUsername(data.getString("username"));
                otherUser.setId(data.getInt("id"));
                users.add(otherUser);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public static int getBalance(User user) {
        String[] columns = {COL_AMOUNT};
        String[] selection = {COL_USER_ID + "=?"};
        String sql = sqlQueryBuilder(TABLE_ACCOUNTS, columns, selection);

        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, user.getId());
            ResultSet data = ps.executeQuery();

            if (data.next()) {
                return data.getInt("amount");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    private static boolean withdraw(int amount, User source) {
        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            int finalAmount = getBalance(source) - amount;
            if (finalAmount < 0) return false;
            ps.setInt(1, finalAmount);
            String now = LocalDateTime
                    .now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
            ps.setString(2, now);
            ps.setInt(3, source.getId());
            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    private static void deposit(int amount, User target) {
        try (Connection conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            int finalAmount = getBalance(target) + amount;
            ps.setInt(1, finalAmount);
            String now = LocalDateTime
                    .now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
            ps.setString(2, now);
            ps.setInt(3, target.getId());
            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean transfer(int amount, User source, User target) {
        if (!withdraw(amount, source)) return false;
        deposit(amount, target);
        return true;
    }

    private static String sqlQueryBuilder(String table, String[] columns, String[] selection) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        for (int i = 0; i < columns.length; i++) {
            sb.append(columns[i]);
            if (i == columns.length - 1) break;
            sb.append(", ");
        }
        sb.append(" FROM ").append(table);
        sb.append(" WHERE ");
        for (int i = 0; i < selection.length; i++) {
            sb.append(selection[i]);
            if (i == selection.length - 1) break;
            sb.append(" AND ");
        }
        return sb.toString();
    }


    private static void initializeJDBC() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
