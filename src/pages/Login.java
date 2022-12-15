package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.Commands;
import input.CredentialsInput;
import info.User;
import platform.Session;

import java.util.ArrayList;
import java.util.List;

public class Login extends Page {
    public Login(String name, List<String> possibleActions) {
        super(name, possibleActions);
    }

    public void login(CredentialsInput credentials, ArrayNode output) {
        for (User user : Session.getInstance().getAllUsers()) {
            if (user.getCredentials().getName().compareTo(credentials.getName()) == 0 && user.getCredentials().getPassword().compareTo(credentials.getPassword()) == 0) {
                Commands.onPageSuccess(user, output);
                Session.getInstance().setCurrentUser(user);
                Session.getInstance().setCurrentPage(Session.getInstance().getCurrentPage().getNextPages().get(0));
                return;
            }
        }
        Commands.error(output);
        Session.getInstance().setCurrentPage(Session.getInstance().getCurrentPage().getNextPages().get(1));
    }
}
