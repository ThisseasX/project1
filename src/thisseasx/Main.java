package thisseasx;

class Main {

    public static void main(String[] args) {
        User user = Login.login();

        Menu menu = new Menu(user);

        // noinspection StatementWithEmptyBody
        while (menu.executeAction()) ;

        user.bye();
    }
}
