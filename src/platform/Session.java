package platform;

import input.DataInput;

public class Session {
    private static Session instance = null;
    private Session() {

    }
    public static Session getInstance() {
        if (instance == null){
            instance = new Session();
        }
        return instance;
    }
    public void uploadData(DataInput data) {

    }

    public void startSession() {

    }

    public void reset() {
        instance = null;
    }
}
