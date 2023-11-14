package Exception;

public class SignupException extends Exception {
    private final int statusCode;

    public SignupException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
