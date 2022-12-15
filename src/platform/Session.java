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

public class Session {
    private static Session instance = null;
    private ArrayList<Movie> allMovies = new ArrayList<Movie>();
    private ArrayList<User> allUsers = new ArrayList<User>();
    private User currentUser;
    private Page currentPage = PageHierarchy.build();
    private Session() { }
    public static Session getInstance() {
        if (instance == null){
            instance = new Session();
        }
        return instance;
    }

    public ArrayList<Movie> getAllMovies() {
        return allMovies;
    }

    public void setAllMovies(ArrayList<Movie> allMovies) {
        this.allMovies = allMovies;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public void uploadData(DataInput data) {
        for (MovieInput movie : data.getMovies()) {
            allMovies.add(new Movie(movie));
        }
        for (UserInput user : data.getUsers()) {
            allUsers.add(new User(user, allMovies));
        }
    }

    public void startSession(ArrayList<ActionInput> actions, ArrayNode output) {
        for (ActionInput action : actions) {
            switch (action.getType()) {
                case "change page":
                    Commands.changePage(action, output);
                    break;
                case "on page":
                    Commands.onPage(action, output);
                    break;
                default:
                    break;
            }
        }

    }

    public void reset() {
        instance = null;
    }
}
