package thisseasx;

public class Main {

    public static void main(String[] args) {
        User user = Login.login();
        user.setStatement(new Statement(user));

        Menu menu = Menu.loadMenu(user);
        // noinspection StatementWithEmptyBody
        while (menu.executeAction()) ;

        user.bye();
    }
}
