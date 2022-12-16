package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.Commands;
import info.Movie;
import info.User;
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
        currentMovie = null;
        for (Movie movie : ((Movies) this.getNextPage("movies")).getSelectedMovies()) {
            if (movie.getName().compareTo(action.getMovie()) == 0) {
                currentMovie = movie;
            }
        }

        if (currentMovie == null) {
            Commands.error(output);
        } else {
            ArrayList<Movie> newMovies = new ArrayList<>();
            newMovies.add(new Movie(currentMovie));
            Commands.searchAndFilterSuccess(Session.getInstance().getCurrentUser(), newMovies,
                    output);
            Session.getInstance().setCurrentPage(this);
        }
    }

    public void purchase(final User currentUser, final ArrayNode output) {
        if (currentMovie == null) {
            Commands.error(output);
            return;
        }
        if (currentUser.getCredentials().getAccountType().compareTo("premium") == 0) {
            if (currentUser.getNumFreePremiumMovies() > 0) {
                currentUser.setNumFreePremiumMovies(currentUser.getNumFreePremiumMovies() - 1);
                currentUser.getPurchasedMovies().add(currentMovie);
                ArrayList<Movie> newMovies = new ArrayList<>();
                newMovies.add(new Movie(currentMovie));
                Commands.searchAndFilterSuccess(Session.getInstance().getCurrentUser(), newMovies,
                        output);
            } else {
                if (currentUser.getTokensCount() < 2) {
                    Commands.error(output);
                } else {
                    currentUser.setTokensCount(currentUser.getTokensCount() - 2);
                    currentUser.getPurchasedMovies().add(currentMovie);
                    ArrayList<Movie> newMovies = new ArrayList<>();
                    newMovies.add(new Movie(currentMovie));
                    Commands.searchAndFilterSuccess(Session.getInstance().getCurrentUser(), newMovies,
                            output);
                }
            }
        } else {
            if (currentUser.getTokensCount() < 2) {
                Commands.error(output);
            } else {
                currentUser.setTokensCount(currentUser.getTokensCount() - 2);
                currentUser.getPurchasedMovies().add(currentMovie);
                ArrayList<Movie> newMovies = new ArrayList<>();
                newMovies.add(new Movie(currentMovie));
                Commands.searchAndFilterSuccess(Session.getInstance().getCurrentUser(), newMovies,
                        output);
            }
        }
    }

    public void watch(final User currentUser, final ArrayNode output) {
        if (currentMovie == null || !currentUser.getPurchasedMovies().contains(currentMovie)) {
            Commands.error(output);
        } else {
            currentUser.getWatchedMovies().add(currentMovie);
            ArrayList<Movie> newMovies = new ArrayList<>();
            newMovies.add(new Movie(currentMovie));
            Commands.searchAndFilterSuccess(Session.getInstance().getCurrentUser(), newMovies,
                    output);
        }
    }

    public void like(final User currentUser, final ArrayNode output) {
        if (currentMovie == null || !currentUser.getWatchedMovies().contains(currentMovie)) {
            Commands.error(output);
        } else {
            currentMovie.setNumLikes(currentMovie.getNumLikes() + 1);
            currentUser.getLikedMovies().add(currentMovie);
            ArrayList<Movie> newMovies = new ArrayList<>();
            newMovies.add(new Movie(currentMovie));
            Commands.searchAndFilterSuccess(Session.getInstance().getCurrentUser(), newMovies,
                    output);
        }
    }

    public void rate(final int rate, final User currentUser, final ArrayNode output) {
        if (currentMovie == null || !currentUser.getWatchedMovies().contains(currentMovie)
                || rate > 5 || rate < 0) {
            Commands.error(output);
        } else {
            currentMovie.setSumRatings(currentMovie.getSumRatings() + rate);
            currentMovie.setNumRatings(currentMovie.getNumRatings() + 1);
            currentMovie.setRating((double) currentMovie.getSumRatings()
                    / currentMovie.getNumRatings());
            currentUser.getRatedMovies().add(currentMovie);
            ArrayList<Movie> newMovies = new ArrayList<>();
            newMovies.add(new Movie(currentMovie));
            Commands.searchAndFilterSuccess(Session.getInstance().getCurrentUser(), newMovies,
                    output);
        }
    }
}
