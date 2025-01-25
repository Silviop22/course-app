package al.sda.shared;

public class WrongCredentialsException extends Exception {
    public WrongCredentialsException() {
        super("Wrong credentials");
    }
}
