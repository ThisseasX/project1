package thisseasx;

public class LoginScreen {

    public static void main(String[] args) {
        //noinspection StatementWithEmptyBody
        while (!DatabaseConnector.login()) {
        }
    }
}
