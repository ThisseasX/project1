package thisseasx.ObserverTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ObserverTest {

    private static final String
            RED = "\u001b[31m",
            GREEN = "\u001b[32m",
            YELLOW = "\u001b[33m",
            BLUE = "\u001b[34m",
            MAGENTA = "\u001b[35m",
            CYAN = "\u001b[36m",
            RESET = "\u001b[0m";

    enum Command {
        ADD("Type '1' to ADD an observer to a product."),
        REMOVE("Type '2' to REMOVE an observer from a product."),
        NOTIFY("Type '3' to NOTIFY all observers about product availability."),
        CLEAR("Type '4' to CLEAR all observers from all products.");

        private final String hint;

        Command(String hint) {
            this.hint = hint;
        }
    }

    private static final Scanner sc = new Scanner(System.in);

    private static final List<Product> products = new ArrayList<>(
            Arrays.asList(
                    (new Product("Guitar", false)),
                    (new Product("Sub Woofer", false)),
                    (new Product("Television", false))
            )
    );
    private static final List<Person> people = new ArrayList<>(
            Arrays.asList(
                    (new Person("thiss")),
                    (new Person("olga")),
                    (new Person("dimou"))
            )
    );

    public static void main(String[] args) {
        input(15);
    }

    @SuppressWarnings("SameParameterValue")
    private static void input(int times) {
        for (int i = 0; i < times; i++) {
            input();
        }
    }

    private static void input() {
        showInitialHint();

        List<Command> commandList = Arrays.asList(Command.values());
        int i = getCommand(commandList) - 1;
        Command command = commandList.get(i);

        switch (command) {
            case ADD:
                add();
                break;
            case REMOVE:
                remove();
                break;
            case NOTIFY:
                notifyAllObservers();
                break;
            case CLEAR:
                products.forEach(Product::clearObservers);
                System.out.println();
                break;
        }
        showObservationStatus();
    }

    private static void add() {
        showAvailableProducts(true);
        int product = getCommand(products) - 1;

        showAvailablePeople(product, true);
        int observer = getCommand(people) - 1;

        addToList(product, observer);
    }

    private static void remove() {
        showAvailableProducts(false);
        int product = getCommand(products) - 1;

        showAvailablePeople(product, false);
        int observer = getCommand(people) - 1;

        removeFromList(product, observer);
    }

    private static void notifyAllObservers() {
        String notifying = YELLOW + "@@@ NOTIFYING ALL OBSERVERS @@@" + RESET;
        System.out.println(notifying);
        products.forEach(Product::notifyObservers);
        System.out.println(notifying);
        System.out.println();
    }

    private static void showObservationStatus() {
        String status = CYAN + "*** OBSERVATION STATUS ***" + RESET;
        System.out.println(status);
        boolean empty = true;
        for (Product product : products) {
            if (product.getObservers().size() < 1) continue;
            System.out.printf("Product %s%s%s is observed by:", GREEN, product.getProductName(), RESET);
            for (Observer observer : product.getObservers()) {
                System.out.printf(" %s%s%s", RED, ((Person) observer).getName(), RESET);
                empty = false;
            }
            System.out.println();
        }
        if (empty) System.out.println("NO PRODUCTS ARE OBSERVED");
        System.out.println(status + "\n");
    }

    private static void showAvailableProducts(boolean add) {
        showProductHint(add);
        products.forEach(ObserverTest::showProduct);
    }

    private static void showProduct(Product p) {
        StringBuilder sb = new StringBuilder();
        int number = products.indexOf(p) + 1;
        sb.append(number).append(") ").append(p.getProductName());
        if (p.getObservers().size() > 0) sb.append(":");
        for (Observer o : p.getObservers()) {
            sb.append(" ").append(RED).append(((Person) o).getName()).append(RESET);
        }
        System.out.println(sb);
    }

    private static void showAvailablePeople(int product, boolean add) {
        showPeopleHint(product, add);
        for (Person p : people) {
            boolean shouldBeShown = add != products.get(product).contains(p);
            if (shouldBeShown) System.out.printf("%s) %s%n", people.indexOf(p) + 1, p.getName());
        }
    }

    private static void addToList(int product, int observer) {
        Product p = products.get(product);
        Observer o = people.get(observer);
        if (!p.contains(o)) p.addObserver(o);
    }

    private static void removeFromList(int product, int observer) {
        Product p = products.get(product);
        Observer o = people.get(observer);
        p.removeObserver(o);
    }

    private static int getCommand(List list) {
        int size = list.size();
        int command;
        while (true) {
            showCommandHint(size);
            command = sc.nextInt();
            if (command >= 1 && command <= size) break;
        }
        return command;
    }

    private static void showInitialHint() {
        System.out.print(MAGENTA);
        for (Command command : Command.values()) {
            System.out.println(command.hint);
        }
        System.out.print(RESET);
    }

    private static void showCommandHint(int index) {
        StringBuilder sb = new StringBuilder();
        sb.append(BLUE);
        sb.append("\n-- Available Commands: ");
        for (int i = 1; i < index; i++) {
            sb.append(i).append(", ");
        }
        sb.append(index).append(" --");
        sb.append(RESET);
        System.out.println(sb);
    }

    private static void showPeopleHint(int product, boolean add) {
        String action = add ? "OBSERVE" : "REMOVE from";
        System.out.printf("Please select a person to %s %s:%n%n",
                action, products.get(product).getProductName());
        System.out.println(GREEN + "### Available People ###" + RESET);
    }

    private static void showProductHint(boolean add) {
        String action = add ? "ADD" : "REMOVE";
        System.out.printf("Please select a product to %s an observer:%n%n", action);
        System.out.println(GREEN + "### Available Products ###" + RESET);
    }
}
