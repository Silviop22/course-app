package al.sda.user;

import al.sda.shared.Repository;

import java.util.NoSuchElementException;

public class UserRepository implements Repository {

    private User[] users = new User[100];
    public User[] getUsers() {
        return users;
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user != null && user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new NoSuchElementException("Could not find user");
    }

    public void addUser(User value) {
        try {
            User existing = getUser(value.getUsername());
        } catch (NoSuchElementException e) {
            if (!hasFreeSpace()) {
                users = getIncreasedArray();
            }

            saveUser(value);
        }
    }

    private void saveUser(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;

                return;
            }
        }

        throw new RuntimeException("Could not find free slot for user");
    }

    private User[] getIncreasedArray(){
        User[] result = new User[users.length + 10];
        for (int i = 0; i < users.length; i++) {
            result[i] = users[i];
        }
        return result;
    }

    @Override
    public boolean hasFreeSpace() {
        for (User user : users) {
            if (user == null) {
                return true;
            }
        }
        return false;
    }

}
