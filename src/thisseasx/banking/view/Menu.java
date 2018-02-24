package thisseasx.banking.view;

import thisseasx.banking.model.User;
import thisseasx.banking.service.AccountManager;

import java.util.*;

import static thisseasx.banking.util.ANSI.*;

public class Menu {

    private enum Action {
        VIEW_CO_OP_ACCOUNT("View Co-operative's Account."),
        VIEW_MEMBER_ACCOUNTS("View Member Accounts."),
        VIEW_OWN_ACCOUNT("View your Account."),
        WITHDRAW_FROM_MEMBER("Withdraw from another Member's Account."),
        DEPOSIT_TO_MEMBER("Deposit to another Member's Account."),
        DEPOSIT_TO_CO_OP("Deposit to the Co-operative's Account."),
        SEND_STATEMENT("Send today's statement and logout.");

        private final String hint;

        Action(String hint) {
            this.hint = hint;
        }

        @Override
        public String toString() {
            return hint;
        }
    }

    private final AccountManager accountManager;

    private final List<Action> actions = new ArrayList<>();

    public Menu(User user) {
        accountManager = new AccountManager(user);

        if (user.getId() == 1) {
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

    public boolean executeAction() {
        Action action = requestAction();
        switch (action) {
            case VIEW_CO_OP_ACCOUNT:
                accountManager.viewCoOpAccount();
                break;
            case VIEW_MEMBER_ACCOUNTS:
                accountManager.viewMemberAccounts();
                break;
            case VIEW_OWN_ACCOUNT:
                accountManager.viewOwnAccount();
                break;
            case WITHDRAW_FROM_MEMBER:
                accountManager.withdrawFromMember();
                break;
            case DEPOSIT_TO_MEMBER:
                accountManager.depositToMember();
                break;
            case DEPOSIT_TO_CO_OP:
                accountManager.depositToCoOp();
                break;
            case SEND_STATEMENT:
                accountManager.sendStatement();
                return false;
        }
        return true;
    }

    private Action requestAction() {
        Scanner sc = new Scanner(System.in);
        int actionIndex;
        while (true) {
            printActions();
            //noinspection Duplicates
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
}