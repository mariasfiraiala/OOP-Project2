package input;

import java.util.ArrayList;

public class DataInput {
    ArrayList<UserInput> users = new ArrayList<UserInput>();
    ArrayList<MovieInput> movies = new ArrayList<MovieInput>();
    ArrayList<ActionInput> actions = new ArrayList<ActionInput>();

    public DataInput() { }

    public ArrayList<UserInput> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserInput> users) {
        this.users = users;
    }

    public ArrayList<MovieInput> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MovieInput> movies) {
        this.movies = movies;
    }

    public ArrayList<ActionInput> getActions() {
        return actions;
    }

    public void setActions(ArrayList<ActionInput> actions) {
        this.actions = actions;
    }
}
