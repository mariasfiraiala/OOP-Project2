package pages;

import java.util.List;

public class PageHierarchy {
    public static Page build() {
        Authenticated authenticated = new Authenticated("HomePage",
                List.of());
        Login login = new Login("login",
                List.of("login"));
        Register register = new Register("register",
                List.of("register"));
        Movies movies = new Movies("movies",
                List.of("search", "filter"));
        SeeDetails seeDetails = new SeeDetails("see details",
                List.of("purchase", "watch", "like", "rate"));
        Upgrades upgrades = new Upgrades("upgrades",
                List.of("buy premium", "buy tokens"));
        Logout logout = new Logout("logout",
                List.of());

        authenticated.setNextPages(List.of(movies, upgrades, logout));
        login.setNextPages(List.of());
        register.setNextPages(List.of());
        movies.setNextPages(List.of(authenticated, seeDetails, logout, movies));
        seeDetails.setNextPages(List.of(authenticated, movies, upgrades, logout));
        upgrades.setNextPages(List.of(authenticated, movies, logout));
        logout.setNextPages(List.of(register, login));

        return logout;
    }
}
