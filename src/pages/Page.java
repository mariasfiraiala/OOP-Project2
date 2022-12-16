package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionInput;
import platform.Session;

import java.util.ArrayList;
import java.util.List;

public abstract class Page {
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

    public void changePage(ActionInput action, ArrayNode output) {
        Session.getInstance().setCurrentPage(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Page> getNextPages() {
        return nextPages;
    }

    public Page getNextPage(String name) {
        Page nextPage = null;
        for (Page page : nextPages) {
            if (page.getName().compareTo(name) == 0) {
                nextPage = page;
            }
        }
        return nextPage;
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