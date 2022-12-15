package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.Commands;
import info.Movie;
import input.FiltersInput;
import platform.Session;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Movies extends Page {
    public Movies(String name, List<String> possibleActions) {
        super(name, possibleActions);
    }

    public void search(String startsWith, ArrayNode output) {
        ArrayList<Movie> tmpCurrentMovies = new ArrayList<>();

        for (Movie movie : Session.getInstance().getCurrentUser().getVisibleMovies()) {
            if (movie.getName().contains(startsWith)) {
                tmpCurrentMovies.add(movie);
            }
        }

        Commands.searchAndFilterSuccess(Session.getInstance().getCurrentUser(), tmpCurrentMovies, output);
    }

    public void filter(FiltersInput filter, ArrayNode output) {
        ArrayList<Movie> tmpCurrentMovies = new ArrayList<>();
        tmpCurrentMovies.addAll(Session.getInstance().getCurrentUser().getVisibleMovies());

        if (filter.getContains() != null) {
            if (filter.getContains().getActors() != null) {
                for (String actor : filter.getContains().getActors()) {
                    Iterator<Movie> movie = tmpCurrentMovies.iterator();
                    while (movie.hasNext()) {
                        Movie next = movie.next();
                        if (!next.getActors().contains(actor)) {
                            movie.remove();
                        }
                    }
                }
            }

            if (filter.getContains().getGenre() != null) {
                for (String genre : filter.getContains().getGenre()) {
                    Iterator<Movie> movie = tmpCurrentMovies.iterator();
                    while (movie.hasNext()) {
                        Movie next = movie.next();
                        if (!next.getActors().contains(genre)) {
                            movie.remove();
                        }
                    }
                }
            }
        }

        if (filter.getSort() != null) {
            if (filter.getSort().getRating() != null) {
                sortByRating(tmpCurrentMovies, filter.getSort().getRating());
            }
            if (filter.getSort().getDuration() != null) {
                sortByDuration(tmpCurrentMovies, filter.getSort().getDuration());
            }
        }
        Commands.searchAndFilterSuccess(Session.getInstance().getCurrentUser(), tmpCurrentMovies, output);
    }

    private void sortByRating(ArrayList<Movie> movies, String mode) {
        if (mode.compareTo("decreasing") == 0) {
            movies.sort(Comparator.comparingDouble(Movie::getRating).reversed());
        } else {
            movies.sort(Comparator.comparingDouble(Movie::getRating));
        }
    }

    private void sortByDuration(ArrayList<Movie> movies, String mode) {
        if (mode.compareTo("decreasing") == 0) {
            movies.sort(Comparator.comparingDouble(Movie::getDuration).reversed());
        } else {
            movies.sort(Comparator.comparingDouble(Movie::getDuration));
        }
    }
}
