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

    public Page(final String name, final List<String> possibleActions) {
        this.name = name;
        this.possibleActions = possibleActions;
    }

    /**
     *
     * @param action
     * @param output
     */
    public void changePage(final ActionInput action, final ArrayNode output) {
        Session.getInstance().setCurrentPage(this);
    }
    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final  List<Page> getNextPages() {
        return nextPages;
    }

    public final Page getNextPage(final String pageName) {
        Page pageNext = null;
        for (Page page : nextPages) {
            if (page.getName().compareTo(pageName) == 0) {
                pageNext = page;
            }
        }
        return pageNext;
    }

    public final void setNextPages(final List<Page> nextPages) {
        this.nextPages = nextPages;
    }

    public final List<String> getPossibleActions() {
        return possibleActions;
    }

    public final void setPossibleActions(final ArrayList<String> possibleActions) {
        this.possibleActions = possibleActions;
    }
}
