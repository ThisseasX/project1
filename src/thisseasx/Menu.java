package thisseasx;

import java.util.*;

import static thisseasx.ANSI.*;

class Menu {

    private final User user;

    private List<Action> actions = new ArrayList<>();

    static Menu loadMenu(User user) {
        return new Menu(user);
    }

    private Menu(User user) {
        this.user = user;
        if (user.isAdmin()) {
            actions.addAll(Arrays.asList(
                    Action.VIEW_CO_OP_ACCOUNT,
                    Action.VIEW_MEMBER_ACCOUNTS,
                    Action.DEPOSIT_TO_MEMBER,
                    Action.WITHDRAW_FROM_MEMBER,
                    Action.SEND_STATEMENT
            ));
        } else {
            actions.addAll(Arrays.asList(
                    Action.VIEW_OWN_ACCOUNT,
                    Action.DEPOSIT_TO_CO_OP,
                    Action.DEPOSIT_TO_MEMBER,
                    Action.SEND_STATEMENT
            ));
        }
    }

    boolean executeAction() {
        Action action = requestAction();
        switch (action) {
            case VIEW_CO_OP_ACCOUNT:
                Accounts.viewCoOpAccount(user);
                break;
            case VIEW_MEMBER_ACCOUNTS:
                Accounts.viewMemberAccounts(user);
                break;
            case VIEW_OWN_ACCOUNT:
                Accounts.viewOwnAccount(user);
                break;
            case WITHDRAW_FROM_MEMBER:
                Accounts.withdrawFromMember(user);
                break;
            case DEPOSIT_TO_MEMBER:
                Accounts.depositToMember(user);
                break;
            case DEPOSIT_TO_CO_OP:
                Accounts.depositToCoOp(user);
                break;
            case SEND_STATEMENT:
                Accounts.sendStatement(user);
                return false;
        }
        return true;
    }

    private Action requestAction() {
        Scanner sc = new Scanner(System.in);
        int actionIndex;
        while (true) {
            printActions();
            try {
                actionIndex = sc.nextInt();
                if (actionIndex > 0 && actionIndex <= actions.size()) break;
                printColored(RED, String.format("--- You must input a number between 1 and %s ---", actions.size()));
            } catch (InputMismatchException e) {
                printColored(RED, "--- That's not a number ---");
                sc.nextLine();
            }
        }
        return actions.get(actionIndex - 1);
    }

    private void printActions() {
        actions.forEach(x -> printColored(YELLOW, String.format(("%s) %s"), actions.indexOf(x) + 1, x)));
    }

    private enum Action {
        VIEW_CO_OP_ACCOUNT("View Co-operative's Account"),
        VIEW_MEMBER_ACCOUNTS("View Member Accounts"),
        VIEW_OWN_ACCOUNT("View your Account"),
        WITHDRAW_FROM_MEMBER("Withdraw from another Member's Account"),
        DEPOSIT_TO_MEMBER("Deposit to another Member's Account"),
        DEPOSIT_TO_CO_OP("Deposit to the Co-operative's Account"),
        SEND_STATEMENT("Send today's statement and logout.");

        private String hint;

        Action(String hint) {
            this.hint = hint;
        }

        @Override
        public String toString() {
            return hint;
        }
    }
}