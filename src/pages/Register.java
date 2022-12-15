package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.Commands;
import info.User;
import input.CredentialsInput;
import input.UserInput;
import platform.Session;

import java.util.ArrayList;
import java.util.List;

public class Register extends Page {
    public Register(String name, List<String> possibleActions) {
        super(name, possibleActions);
    }

    public void register(CredentialsInput credentials, ArrayNode output) {
        for (User user : Session.getInstance().getAllUsers()) {
            if (credentials.getName().compareTo(user.getCredentials().getName()) == 0) {
                Commands.error(output);
                Session.getInstance().setCurrentPage(Session.getInstance().getCurrentPage().getNextPages().get(1));
                return;
            }
        }

        UserInput tmp = new UserInput();
        tmp.setCredentials(credentials);
        User user = new User(tmp, new ArrayList<>());

        Session.getInstance().getAllUsers().add(user);
        Session.getInstance().setCurrentUser(user);
        Session.getInstance().setCurrentPage(Session.getInstance().getCurrentPage().getNextPages().get(0));
        Commands.onPageSuccess(user, output);
    }
}
