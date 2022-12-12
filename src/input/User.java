package input;

import java.util.ArrayList;

public class User {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;
    private int leftMovies = 15;

    private ArrayList<Movie> currentMovies = new ArrayList<Movie>();
    private ArrayList<Movie> likedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> purchasedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> watchedMovies = new ArrayList<Movie>();
    private ArrayList<Movie> ratedMovies = new ArrayList<Movie>();

    public User() {

    }

    public User(String name, String password, String accountType, String country, String balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }

    public User(String name, String password, String accountType, String country, String balance,
                int leftMovies, ArrayList<Movie> currentMovies, ArrayList<Movie> likedMovies,
                ArrayList<Movie> purchasedMovies, ArrayList<Movie> watchedMovies,
                ArrayList<Movie> ratedMovies) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
        this.leftMovies = leftMovies;
        this.currentMovies = currentMovies;
        this.likedMovies = likedMovies;
        this.purchasedMovies = purchasedMovies;
        this.watchedMovies = watchedMovies;
        this.ratedMovies = ratedMovies;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCountry() {
        return country;
    }

    public String getBalance() {
        return balance;
    }

    public ArrayList<Movie> getCurrentMovies() {
        return currentMovies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setCurrentMovies(ArrayList<Movie> currentMovies) {
        this.currentMovies = currentMovies;
    }

    public int getLeftMovies() {
        return leftMovies;
    }

    public void setLeftMovies(int leftMovies) {
        this.leftMovies = leftMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
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

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }
}
