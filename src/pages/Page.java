package pages;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private String name;
    private List<Page> nextPages;
    private List<String> possibleActions;

    public String getName() {
        return name;
    }

    public Page(String name, List<String> possibleActions) {
        this.name = name;
        this.possibleActions = possibleActions;
    }

    public void setName(String name) {
        this.name = name;
    }

    List<Page> getNextPages() {
        return nextPages;
    }

    public void setNextPages(List<Page> nextPages) {
        this.nextPages = nextPages;
    }

    public List<String> getPossibleActions() {
        return possibleActions;
    }

    public void setPossibleActions(ArrayList<String> possibleActions) {
        this.possibleActions = possibleActions;
    }
}