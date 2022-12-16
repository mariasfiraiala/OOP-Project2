package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import info.User;
import input.ActionInput;
import platform.Session;

import java.util.List;

public class Logout extends Page {
    public Logout(String name, List<String> possibleActions) {
        super(name, possibleActions);
    }

    public void changePage(ActionInput action, ArrayNode output) {
        Session.getInstance().setCurrentPage(this);
        Session.getInstance().setCurrentUser(null);
    }
}
