package info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import input.UserInput;

import java.util.ArrayList;

public class User extends UserInput {
    private int tokensCount;
    private int numFreePremiumMovies = 15;
    @JsonIgnore
    private ArrayList<Movie> visibleMovies = new ArrayList<Movie>();
    private ArrayList<Movie> purchasedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> watchedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> likedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> ratedMovies = new ArrayList<Movie>();

    public User() { }

    public User(UserInput user, ArrayList<Movie> movies) {
        super(user);
        for (Movie movie : movies) {
            if (!movie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                visibleMovies.add(movie);
            }
        }
    }

    public User(User user) {
        super(user);
        this.tokensCount = user.tokensCount;
        this.numFreePremiumMovies = user.numFreePremiumMovies;
        this.visibleMovies = user.visibleMovies;
        this.purchasedMovies = user.purchasedMovies;
        this.watchedMovies = user.watchedMovies;
        this.likedMovies = user.likedMovies;
        this.ratedMovies = user.ratedMovies;
    }

    public ArrayList<Movie> getVisibleMovies() {
        return visibleMovies;
    }

    public void setVisibleMovies(ArrayList<Movie> visibleMovies) {
        this.visibleMovies = visibleMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }
}
