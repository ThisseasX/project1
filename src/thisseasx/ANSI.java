package thisseasx;

class ANSI {
    static final String
            RED = "\u001b[31m",
            GREEN = "\u001b[32m",
            YELLOW = "\u001b[33m",
            BLUE = "\u001b[34m",
            MAGENTA = "\u001b[35m",
            CYAN = "\u001b[36m",
            RESET = "\u001b[0m";

    static void printColored(String color, String text) {
        StringBuilder sb = new StringBuilder();
        sb.append(color).append(text).append(RESET);
        System.out.println(sb);
    }
}
