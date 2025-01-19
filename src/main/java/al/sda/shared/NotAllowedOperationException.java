package al.sda.shared;

public class NotAllowedOperationException extends RuntimeException {
    public NotAllowedOperationException(String message) {
        super(message);
    }
}
