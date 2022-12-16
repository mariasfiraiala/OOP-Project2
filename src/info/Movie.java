package info;

import input.MovieInput;

public class Movie extends MovieInput {
    private double rating;
    private int numRatings;
    private int numLikes;

    public Movie(MovieInput movie) {
        super(movie);
        rating = 0;
        numRatings = 0;
        numLikes = 0;
    }

    public Movie(Movie movie) {
        super(movie);
        this.rating = movie.rating;
        this.numRatings = movie.numRatings;
        this.numLikes = movie.numLikes;
    }
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public int getNumLikes() { return numLikes; }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }
}
