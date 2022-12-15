package commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import info.Movie;
import info.User;
import input.ActionInput;
import pages.*;
import platform.Session;

import java.util.ArrayList;

public class Commands {
    private Commands() { }

    public static void changePage(ActionInput action, ArrayNode output) {
        Page nextPage = null;
        for (Page page : Session.getInstance().getCurrentPage().getNextPages()) {
            if (page.getName().compareTo(action.getPage()) == 0) {
                nextPage = page;
            }
        }

        if (nextPage == null) {
            error(output);
        } else {
            switch (action.getPage()) {
                case "movies", "see details" -> changePageSuccess(Session.getInstance().getCurrentUser(), output);
                case "logout" -> ((Logout) nextPage).logout(Session.getInstance().getCurrentUser());
                case "register" -> System.out.println("ceva");
            }
            Session.getInstance().setCurrentPage(nextPage);
        }
    }

    public static void onPage(ActionInput action, ArrayNode output) {
        Page nextPage = null;
        if (!Session.getInstance().getCurrentPage().getPossibleActions().contains(action.getFeature())) {
            error(output);
        } else {
            switch (action.getFeature()) {
                case "register":
                    ((Register) Session.getInstance().getCurrentPage()).register(action.getCredentials(), output);
                    break;
                case "login":
                    ((Login) Session.getInstance().getCurrentPage()).login(action.getCredentials(), output);
                    break;
                case "search":
                    ((Movies) Session.getInstance().getCurrentPage()).search(action.getStartsWith(), output);
                    break;
            }
        }
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

    public static void searchAndFilerSuccess(User currentUser, ArrayList<Movie> currentMovies, ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.putPOJO("error", null);
        node.putPOJO("currentMoviesList", currentMovies);
        node.putPOJO("currentUser", currentUser);
        output.addPOJO(node);
    }
}
