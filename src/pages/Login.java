package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.Commands;
import input.CredentialsInput;
import info.User;
import platform.Session;
import java.util.List;

public final class Login extends Page {
    public Login(final String name, final List<String> possibleActions) {
        super(name, possibleActions);
    }

    public void login(final CredentialsInput credentials, final ArrayNode output) {
        for (User user : Session.getInstance().getAllUsers()) {
            if (user.getCredentials().getName().compareTo(credentials.getName()) == 0
                    && user.getCredentials().getPassword().compareTo(credentials.
                    getPassword()) == 0) {
                Commands.onPageSuccess(user, output);
                Session.getInstance().setCurrentUser(user);
                Session.getInstance().setCurrentPage(this.getNextPage("authenticated"));
                return;
            }
        }
        Commands.error(output);
        Session.getInstance().setCurrentPage(this.getNextPage("logout"));
    }
}
