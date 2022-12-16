package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.Commands;
import info.Movie;
import info.User;
import input.ActionInput;
import platform.Session;

import java.util.ArrayList;
import java.util.List;

public class SeeDetails extends Page {
    private Movie currentMovie;
    public SeeDetails(String name, List<String> possibleActions) {
        super(name, possibleActions);
    }

    public void changePage(ActionInput action, ArrayNode output) {
        Movie currentMovie = null;
        for (Movie movie : ((Movies) this.getNextPages().get(1)).getSelectedMovies()) {
            if (movie.getName().compareTo(action.getMovie()) == 0) {
                currentMovie = movie;
            }
        }

        if (currentMovie == null) {
            Commands.error(output);
        } else {
            ArrayList<Movie> newMovies = new ArrayList<>();
            newMovies.add(new Movie(currentMovie));
            Commands.searchAndFilterSuccess(Session.getInstance().getCurrentUser(), newMovies, output);
            Session.getInstance().setCurrentPage(this);
        }
    }
}
