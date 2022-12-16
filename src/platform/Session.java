package platform;

import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.Commands;
import pages.Page;
import input.ActionInput;
import input.DataInput;
import input.MovieInput;
import input.UserInput;
import info.Movie;
import info.User;
import pages.PageHierarchy;

import java.util.ArrayList;

public final class Session {
    private static Session instance = null;
    private ArrayList<Movie> allMovies = new ArrayList<Movie>();
    private ArrayList<User> allUsers = new ArrayList<User>();
    private User currentUser;
    private Page currentPage = PageHierarchy.build();
    private Session() { }
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public ArrayList<Movie> getAllMovies() {
        return allMovies;
    }

    public void setAllMovies(final ArrayList<Movie> allMovies) {
        this.allMovies = allMovies;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(final ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    public void uploadData(final DataInput data) {
        for (MovieInput movie : data.getMovies()) {
            allMovies.add(new Movie(movie));
        }
        for (UserInput user : data.getUsers()) {
            allUsers.add(new User(user, allMovies));
        }
    }

    public void startSession(final ArrayList<ActionInput> actions, final ArrayNode output) {
        for (ActionInput action : actions) {
            switch (action.getType()) {
                case "change page" -> Commands.changePage(action, output);
                case "on page" -> Commands.onPage(action, output);
                default -> {
                }
            }
        }

    }

    public void reset() {
        instance = null;
    }
}
