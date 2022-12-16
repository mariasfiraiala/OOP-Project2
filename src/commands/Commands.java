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

public final class Commands {
    private Commands() { }

    public static void changePage(final ActionInput action, final ArrayNode output) {
        Page nextPage = null;
        for (Page page : Session.getInstance().getCurrentPage().getNextPages()) {
            if (page.getName().compareTo(action.getPage()) == 0) {
                nextPage = page;
            }
        }

        if (nextPage == null) {
            error(output);
        } else {
            nextPage.changePage(action, output);
        }
    }

    public static void onPage(final ActionInput action, final ArrayNode output) {
        if (!Session.getInstance().getCurrentPage().getPossibleActions().contains(action.
                getFeature())) {
            error(output);
        } else {
            switch (action.getFeature()) {
                case "register":
                    ((Register) Session.getInstance().getCurrentPage()).register(action.
                            getCredentials(), output);
                    break;
                case "login":
                    ((Login) Session.getInstance().getCurrentPage()).login(action.getCredentials(),
                            output);
                    break;
                case "search":
                    ((Movies) Session.getInstance().getCurrentPage()).search(action.getStartsWith(),
                            output);
                    break;
                case "filter":
                    ((Movies) Session.getInstance().getCurrentPage()).filter(action.getFilters(),
                            output);
                    break;
                case "buy tokens":
                    ((Upgrades) Session.getInstance().getCurrentPage()).buyTokens(action,
                            Session.getInstance().getCurrentUser(), output);
                    break;
                case "buy premium account":
                    ((Upgrades) Session.getInstance().getCurrentPage()).buyPremium(Session.
                            getInstance().getCurrentUser(), output);
                    break;
                case "purchase":
                    ((SeeDetails) Session.getInstance().getCurrentPage()).purchase(Session.
                            getInstance().getCurrentUser(), output);
                    break;
                case "watch":
                    ((SeeDetails) Session.getInstance().getCurrentPage()).watch(Session.
                            getInstance().getCurrentUser(), output);
                    break;
                case "like":
                    ((SeeDetails) Session.getInstance().getCurrentPage()).like(Session.
                            getInstance().getCurrentUser(), output);
                    break;
                case "rate":
                    ((SeeDetails) Session.getInstance().getCurrentPage()).rate(action.getRate(),
                            Session.getInstance().getCurrentUser(), output);
                default:
                    break;
            }
        }
    }

    public static void error(final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("error", "Error");
        node.putPOJO("currentMoviesList", new ArrayList<>());
        node.putPOJO("currentUser", null);
        output.addPOJO(node);
    }

    public static void changePageSuccess(final User currentUser, final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.putPOJO("error", null);
        node.putPOJO("currentMoviesList", deepCopy(currentUser.getVisibleMovies()));
        node.putPOJO("currentUser", new User(currentUser));
        output.addPOJO(node);
    }

    public static void onPageSuccess(final User currentUser, final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.putPOJO("error", null);
        node.putPOJO("currentMoviesList", new ArrayList<>());
        node.putPOJO("currentUser", new User(currentUser));
        output.addPOJO(node);
    }

    public static void searchAndFilterSuccess(final User currentUser,
                                              final ArrayList<Movie> currentMovies,
                                              final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.putPOJO("error", null);
        node.putPOJO("currentMoviesList", deepCopy(currentMovies));
        node.putPOJO("currentUser", new User(currentUser));
        output.addPOJO(node);
    }

    public static ArrayList<Movie> deepCopy(final ArrayList<Movie> movies) {
        ArrayList<Movie> newMovies = new ArrayList<>();

        for (Movie movie : movies) {
            newMovies.add(new Movie(movie));
        }

        return newMovies;
    }
}
