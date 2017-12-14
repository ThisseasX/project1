package thisseasx;

public class LoginScreen {

    public static void main(String[] args) {
        DBConnector dbc = new DBConnector();
        // noinspection StatementWithEmptyBody
        while (!dbc.login()) ;
        MenuScreen.main(new String[]{dbc.getUsername(), String.valueOf(dbc.isAdmin())});
    }
}
