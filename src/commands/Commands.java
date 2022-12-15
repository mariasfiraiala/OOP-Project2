package commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import info.User;
import input.ActionInput;
import pages.Login;
import pages.Logout;
import pages.Page;
import pages.Register;

import java.util.ArrayList;

public class Commands {
    private Commands() { }

    public static Page changePage(ActionInput action, Page currentPage, User currentUser, ArrayNode output) {
        Page nextPage = null;
        for (Page page : currentPage.getNextPages()) {
            if (page.getName().compareTo(action.getPage()) == 0) {
                nextPage = page;
            }
        }

        if (nextPage == null) {
            error(output);
        } else {
            switch (action.getPage()) {
                case "movies", "see details" -> changePageSuccess(currentUser, output);
                case "logout" -> ((Logout) nextPage).logout(currentUser);
                case "register" -> System.out.println("ceva");
            }
        }

        return nextPage;
    }

    public static Page onPage(ActionInput action, Page currentPage, User currentUser, ArrayList<User> allUsers, ArrayNode output) {
        Page nextPage = null;
        if (!currentPage.getPossibleActions().contains(action.getFeature())) {
            error(output);
        } else {
            switch (action.getFeature()) {
                case "register":
                    nextPage = ((Register) currentPage).register(action.getCredentials(), currentPage, currentUser, allUsers, output);
                    break;
                case "login":
                    nextPage = ((Login) currentPage).login(action.getCredentials(), currentPage, currentUser,  allUsers, output);
                    break;
            }
        }
        return nextPage;
    }

    public static void error(ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("error", "Error");
        node.putPOJO("currentMoviesList", new ArrayList<>());
        node.putPOJO("currentUser", null);
        output.addPOJO(node);
    }

    public static void changePageSuccess(User currentUser, ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.putPOJO("error", null);
        node.putPOJO("currentMoviesList", currentUser.getVisibleMovies());
        node.putPOJO("currentUser", currentUser);
        output.addPOJO(node);
    }

    public static void onPageSuccess(User currentUser, ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.putPOJO("error", null);
        node.putPOJO("currentMoviesList", new ArrayList<>());
        node.putPOJO("currentUser", currentUser);
        output.addPOJO(node);
    }

}
