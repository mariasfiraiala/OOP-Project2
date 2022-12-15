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
    private Page currentPage;
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

    public void uploadData(DataInput data) {
        for (MovieInput movie : data.getMovies()) {
            allMovies.add(new Movie(movie));
        }
        for (UserInput user : data.getUsers()) {
            allUsers.add(new User(user, allMovies));
        }
    }

    public void startSession(ArrayList<ActionInput> actions, ArrayNode output) {
        Page currentPage = PageHierarchy.build();
        User currentUser = new User();
        currentUser.setIsRegistered(false);

        for (ActionInput action : actions) {
            switch (action.getType()) {
                case "change page":
                    Page nextPage = Commands.changePage(action, currentPage, currentUser, output);
                    if (nextPage != null) {
                        currentPage = nextPage;
                    }
                    break;
                case "on page":
                    nextPage = Commands.onPage(action, currentPage, currentUser, allUsers, output);
                    if (nextPage != null) {
                        currentPage = nextPage;
                    }
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
