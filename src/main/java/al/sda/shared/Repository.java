package al.sda.shared;

public interface Repository {
    boolean hasFreeSpace();

    default boolean hasFreeSpace(Object[] array){
        for (Object o : array) {
            if (o == null) {
                return true;
            }
        }
        return false;
    }
}
