package thisseasx;

import com.sun.xml.internal.ws.util.StringUtils;

public class MenuScreen {

    public static void main(String[] args) {
        String accessLevel = Boolean.parseBoolean(args[1]) ? "admin" : "user";
        String welcome = String.format("Welcome %s %s! How may I help you today?%n",
                accessLevel, StringUtils.capitalize(args[0]));
        ANSI.printColored(ANSI.BLUE, welcome);
    }
}
