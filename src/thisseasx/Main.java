package thisseasx;

import thisseasx.model.User;
import thisseasx.service.Login;
import thisseasx.util.CLS;
import thisseasx.view.Menu;

@SuppressWarnings("StatementWithEmptyBody")
class Main {

    public static void main(String[] args) {
        CLS.cls();

        User user = Login.login();

        Menu menu = new Menu(user);

        while (menu.executeAction()) ;

        user.bye();
    }
}
