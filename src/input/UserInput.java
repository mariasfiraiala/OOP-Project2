package input;

public class UserInput {
    private CredentialsInput credentials;
    public UserInput() { }
    public UserInput(UserInput user) {
        this.credentials = new CredentialsInput(user.credentials);
    }

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsInput credentials) {
        this.credentials = credentials;
    }
}
