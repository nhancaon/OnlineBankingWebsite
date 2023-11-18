package Exception;

public class CreateException extends Exception {
    private final int statusCode;

    public CreateException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
