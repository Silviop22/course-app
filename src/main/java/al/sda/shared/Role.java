package al.sda.shared;

public enum Role {
    STUDENT,
    CREATOR;

    public static Role fromString(String string) {
            if ("STUDENT".equals(string)) {
                return STUDENT;
            } else if ("CREATOR".equals(string)) {
                return CREATOR;
            }
            throw new IllegalArgumentException("Invalid role: " + string);
        }
    }

