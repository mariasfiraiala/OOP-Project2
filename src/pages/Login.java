package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.Commands;
import input.CredentialsInput;
import info.User;
import java.util.ArrayList;
import java.util.List;

public class Login extends Page {
    public Login(String name, List<String> possibleActions) {
        super(name, possibleActions);
    }

    public Page login(CredentialsInput credentials, Page currentPage, User currentuser, ArrayList<User> allUsers, ArrayNode output) {
        for (User user : allUsers) {
            if (user.getCredentials().getName().compareTo(credentials.getName()) == 0 && user.getCredentials().getPassword().compareTo(credentials.getPassword()) == 0) {
                Commands.onPageSuccess(user, output);
                currentuser.setUser(user);
                return currentPage.getNextPages().get(0);
            }
        }
        Commands.error(output);
        return currentPage.getNextPages().get(1);
    }
}
