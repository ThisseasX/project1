package thisseasx.util;

public class ANSI {

    public static final String
            RED = "",
            GREEN = "",
            YELLOW = "",
            BLUE = "",
            MAGENTA = "",
            CYAN = "";

    private static final String RESET = "";

    public static void printColored(String color, String text) {
        StringBuilder sb = new StringBuilder();
        sb.append(color).append(text).append(RESET);
        System.out.println(sb);
    }

    @SuppressWarnings("SameParameterValue")
    public static void printColoredWithWarning(String color1, String color2, String text) {
        StringBuilder sb = new StringBuilder();
        sb.append(color1);
        sb.append(text.substring(text.indexOf("---"), text.lastIndexOf("---") + 3));
        sb.append(color2);
        sb.append(text.substring(text.lastIndexOf("---") + 3));
        sb.append(RESET);
        System.out.println(sb);
    }
}
