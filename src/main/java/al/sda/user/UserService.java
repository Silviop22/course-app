package al.sda.user;


import java.io.IOException;

public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        saveUser(user);
    }

    private void saveUser(User user) {
        userRepository.addUser(user);
    }

    public User logIn(String username, String password) throws IOException {
        User user = userRepository.getUser(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IOException("Invalid Credentials");
        }

        return user;
    }
}