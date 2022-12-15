package pages;

import info.User;

import java.util.List;

public class Logout extends Page {
    public Logout(String name, List<String> possibleActions) {
        super(name, possibleActions);
    }

    public void logout(User currentUser) {
        currentUser.setIsRegistered(false);
    }
}
