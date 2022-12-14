package info;

import input.DataInput;
import input.MovieInput;
import input.UserInput;

import java.util.ArrayList;

public class User extends UserInput {
    private ArrayList<Movie> visibleMovies = new ArrayList<Movie>();
    private ArrayList<Movie> purchasedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> watchedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> likedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> ratedMovies = new ArrayList<Movie>();

    private String tokens;
    private String balance;
    private int remainingMovies = 15;

    public User(UserInput user, ArrayList<Movie> movies) {
        super(user);
        for (Movie movie : movies) {
            if (!movie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                visibleMovies.add(movie);
            }
        }
    }
}
