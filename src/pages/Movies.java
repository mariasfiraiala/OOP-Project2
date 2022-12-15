package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.Commands;
import info.Movie;
import platform.Session;

import java.util.ArrayList;
import java.util.List;

public class Movies extends Page {
    public Movies(String name, List<String> possibleActions) {
        super(name, possibleActions);
    }

    public void search(String startsWith, ArrayNode output) {
        ArrayList<Movie> tmpCurrentMovies = new ArrayList<>();

        for (Movie movie : Session.getInstance().getCurrentUser().getVisibleMovies()) {
            if (movie.getName().contains(startsWith) == true) {
                tmpCurrentMovies.add(movie);
            }
        }

        Commands.searchAndFilerSuccess(Session.getInstance().getCurrentUser(), tmpCurrentMovies, output);
    }
}
