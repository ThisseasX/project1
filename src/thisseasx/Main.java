package thisseasx;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        User user = Login.login();
        Statement statement = new Statement(user);
        System.out.println(statement.getFileName());
        System.out.println(statement.getHistory());
        List<User> list = DBConnector.getOtherUsers(user);
        list.forEach(System.out::println);
//        Menu menu = Menu.loadMenu(user);
        //noinspection StatementWithEmptyBody
//        while (menu.executeAction()) ;
//        user.bye();
    }
}
