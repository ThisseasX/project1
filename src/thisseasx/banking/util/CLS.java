package thisseasx.banking.util;

import java.io.IOException;

@SuppressWarnings("unused")
class CLS {

    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
