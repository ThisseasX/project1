package thisseasx.banking;

import thisseasx.banking.model.User;
import thisseasx.banking.service.Login;
import thisseasx.banking.util.CLS;
import thisseasx.banking.view.Menu;

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
