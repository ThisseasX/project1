package thisseasx;

import com.sun.xml.internal.ws.util.StringUtils;

public class MenuScreen {

    public static void main(String[] args) {
        String s = args[1].equals("true") ? "admin" : "normal";
        System.out.printf("Welcome %s! %s privileges activated!", StringUtils.capitalize(args[0]), StringUtils.capitalize(s));
    }
}
