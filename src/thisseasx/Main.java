package thisseasx;

class Main {

    public static void main(String[] args) {
        User user = Login.login();

        Menu menu = Menu.loadMenu(user);

        // noinspection StatementWithEmptyBody
        while (menu.executeAction()) ;

        user.bye();
    }
}
