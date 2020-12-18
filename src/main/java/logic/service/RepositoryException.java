package logic.service;

public class RepositoryException extends Throwable {
    public static final String NOT_EXIST = "Object do not exist in repository";
    public static final String EXIST = "Object exist in repository";
    public RepositoryException() {
    }

    public RepositoryException(String objInfo, String message) {
        super(objInfo + " " + message);
    }
}
