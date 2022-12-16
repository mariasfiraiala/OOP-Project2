package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.Commands;
import info.Movie;
import input.ActionInput;
import input.FiltersInput;
import platform.Session;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class Movies extends Page {
    private ArrayList<Movie> selectedMovies;
    public Movies(final String name, final List<String> possibleActions) {
        super(name, possibleActions);
    }

    public void changePage(final ActionInput action, final ArrayNode output) {
        selectedMovies = Session.getInstance().getCurrentUser().getVisibleMovies();
        Commands.searchAndFilterSuccess(Session.getInstance().getCurrentUser(), selectedMovies,
                output);
        Session.getInstance().setCurrentPage(this);
    }

    public void search(final String startsWith, final ArrayNode output) {
        ArrayList<Movie> tmpCurrentMovies = new ArrayList<>();

        for (Movie movie : Session.getInstance().getCurrentUser().getVisibleMovies()) {
            if (movie.getName().startsWith(startsWith)) {
                tmpCurrentMovies.add(movie);
            }
        }

        selectedMovies = tmpCurrentMovies;
        Commands.searchAndFilterSuccess(Session.getInstance().getCurrentUser(), tmpCurrentMovies,
                output);
    }

    public void filter(final FiltersInput filter, final ArrayNode output) {
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
                        if (!next.getGenres().contains(genre)) {
                            movie.remove();
                        }
                    }
                }
            }
        }

        if (filter.getSort() != null) {
            if (filter.getSort().getRating() != null && filter.getSort().getDuration() != null) {
                sortByRatingAndDuration(tmpCurrentMovies, filter.getSort().getRating(), filter.getSort().getDuration());
            }

            if (filter.getSort().getRating() != null) {
                sortByRating(tmpCurrentMovies, filter.getSort().getRating());
            }
            if (filter.getSort().getDuration() != null) {
                sortByDuration(tmpCurrentMovies, filter.getSort().getDuration());
            }
        }
        selectedMovies = tmpCurrentMovies;
        Commands.searchAndFilterSuccess(Session.getInstance().getCurrentUser(), tmpCurrentMovies,
                output);
    }

    private void sortByRatingAndDuration(ArrayList<Movie> movies, String modeRating, String modeDuration) {
        Comparator<Movie> comparator;
        if (modeDuration.compareTo("decreasing") == 0) {
            comparator = Comparator.comparing(Movie::getDuration, Comparator.reverseOrder());
        } else {
            comparator = Comparator.comparing(Movie::getDuration);
        }
        if (modeRating.equals("decreasing")) {
            comparator = comparator.thenComparing(Movie::getRating, Comparator.reverseOrder());
        } else {
            comparator = comparator.thenComparing(Movie::getRating);
        }
        movies.sort(comparator);
    }

    private void sortByRating(final ArrayList<Movie> movies, final String mode) {
        if (mode.compareTo("decreasing") == 0) {
            movies.sort(Comparator.comparingDouble(Movie::getRating).reversed());
        } else {
            movies.sort(Comparator.comparingDouble(Movie::getRating));
        }
    }

    private void sortByDuration(final ArrayList<Movie> movies, final String mode) {
        if (mode.compareTo("decreasing") == 0) {
            movies.sort(Comparator.comparingDouble(Movie::getDuration).reversed());
        } else {
            movies.sort(Comparator.comparingDouble(Movie::getDuration));
        }
    }

    public ArrayList<Movie> getSelectedMovies() {
        return selectedMovies;
    }
}
