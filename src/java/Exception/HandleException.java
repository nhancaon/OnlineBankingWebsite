package Exception;

public class HandleException extends Exception {
    private final int statusCode;

    public HandleException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
