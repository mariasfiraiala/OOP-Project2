package input;

import java.util.ArrayList;

public class ContainsInput {
    private ArrayList<String> actors = new ArrayList<String>();
    private ArrayList<String> genre = new ArrayList<String>();
    private ArrayList<String> country = new ArrayList<String>();

    public ContainsInput() { }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public ArrayList<String> getCountry() {
        return country;
    }

    public void setCountry(ArrayList<String> country) {
        this.country = country;
    }
}
