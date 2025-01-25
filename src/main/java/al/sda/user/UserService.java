package al.sda.user;


import java.io.IOException;

public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(String userName, String password, String role) {
        User user;
        if (role.equals("STUDENT")) {
            user = new Student();
            ((Student)user).setCredit(100);
        } else {
            user = new Creator();
            Certification certification = getDefaultCertification();
            ((Creator) user).setCertification(certification);

        }

        user.setUsername(userName);
        user.setPassword(password);
        user.setEnabled(true);

        saveUser(user);
    }

    public void saveUser(User user) {
        userRepository.addUser(user);
    }

    public User logIn(String username, String password) {
        User user = userRepository.getUser(username);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Invalid Credentials");
        }
        return user;
    }

    private Certification getDefaultCertification() {
        Certification english = new Certification();
        english.setName("Toeffl");
        english.setDescription("I cry english");
        english.setLevel("C2");

        return english;
    }
}