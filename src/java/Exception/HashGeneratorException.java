package Exception;

public class HashGeneratorException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public HashGeneratorException(String message) {
        super(message);
    }

    public HashGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

}
