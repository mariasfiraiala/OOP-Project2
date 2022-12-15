package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.Commands;
import info.User;
import input.CredentialsInput;
import input.UserInput;

import java.util.ArrayList;
import java.util.List;

public class Register extends Page {
    public Register(String name, List<String> possibleActions) {
        super(name, possibleActions);
    }

    public Page register(CredentialsInput credentials, Page currentPage, User currentUser, ArrayList<User> allUsers, ArrayNode output) {
        for (User user : allUsers) {
            if (credentials.getName().compareTo(user.getCredentials().getName()) == 0) {
                Commands.error(output);
                return currentPage.getNextPages().get(1);
            }
        }

        UserInput newUser = new UserInput();
        newUser.setCredentials(credentials);
        User user = new User(newUser, new ArrayList<>());
        allUsers.add(user);
        currentUser.setUser(user);
        Commands.onPageSuccess(user, output);
        return currentPage.getNextPages().get(0);
    }
}
