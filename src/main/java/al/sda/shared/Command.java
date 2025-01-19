package al.sda.shared;

public enum Command {
    LOG_IN("log in"),
    LOG_OUT("log out"),
    SIGN_UP("sign up"),
    LIST("list"),
    BUY("buy"),
    LIST_MY_COURSES("list my courses"),
    CREATE("create"),
    EXIT("exit");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public Command fromValue(String value) {
        for (Command command : Command.values()) {
            if (command.getValue().equals(value)) {
                return command;
            }
        }
        throw new RuntimeException("Not a valid command");
    }

    public String getValue() {
        return value;
    }
}
