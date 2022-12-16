package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionInput;
import platform.Session;

import java.util.List;

public final class Logout extends Page {
    public Logout(final String name, final List<String> possibleActions) {
        super(name, possibleActions);
    }

    public void changePage(final ActionInput action, final ArrayNode output) {
        Session.getInstance().setCurrentPage(this);
        Session.getInstance().setCurrentUser(null);
    }
}
