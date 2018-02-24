package thisseasx.banking.util;

public class ANSI {

    public static final String
            RED = "\u001b[31m",
            GREEN = "\u001b[32m",
            YELLOW = "\u001b[33m",
            BLUE = "\u001b[34m",
            MAGENTA = "\u001b[35m",
            CYAN = "\u001b[36m";

    private static final String RESET = "\u001b[0m";

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
