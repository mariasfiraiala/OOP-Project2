package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.Commands;
import info.Movie;
import input.ActionInput;
import platform.Session;

import java.util.ArrayList;
import java.util.List;

public final class SeeDetails extends Page {
    private Movie currentMovie;
    public SeeDetails(final String name, final List<String> possibleActions) {
        super(name, possibleActions);
    }

    public void changePage(final ActionInput action, final ArrayNode output) {
        Movie movieCurrent = null;
        for (Movie movie : ((Movies) this.getNextPages().get(1)).getSelectedMovies()) {
            if (movie.getName().compareTo(action.getMovie()) == 0) {
                movieCurrent = movie;
            }
        }

        if (movieCurrent == null) {
            Commands.error(output);
        } else {
            ArrayList<Movie> newMovies = new ArrayList<>();
            newMovies.add(new Movie(movieCurrent));
            Commands.searchAndFilterSuccess(Session.getInstance().getCurrentUser(), newMovies,
                    output);
            Session.getInstance().setCurrentPage(this);
        }
    }
}
