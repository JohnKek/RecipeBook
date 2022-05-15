package project.errors;

public class PersistenceException extends RuntimeException {
    public PersistenceException(String msg) {
        super(msg);
    }
}
