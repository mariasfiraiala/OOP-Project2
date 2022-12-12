package input;

import java.util.ArrayList;

public class Data {
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Movie> movies = new ArrayList<Movie>();
    ArrayList<Action> actions = new ArrayList<Action>();

    public Data() {

    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }
}
