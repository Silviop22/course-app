package al.sda.user;

import al.sda.course.Course;
import al.sda.shared.Role;

import java.io.IOException;
import java.time.LocalDate;

public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(String username, String password, Role role) {
        if (role == Role.STUDENT) {
            saveUser(username, password);
            return;
        }

        Creator user = new Creator();
        user.setUsername(username);
        user.setPassword(password);
        saveUser(user);
    }

    public void saveUser(String username, String password) {
        Student student = new Student();
        student.setUsername(username);
        student.setPassword(password);
        student.setRegisterDate(LocalDate.now());
        student.setRole(Role.STUDENT);
        student.setCredit(100);
        saveUser(student);
    }

    private void saveUser(User user) {
        userRepository.addUser(user);
    }

    public User logIn(String username, String password) throws IOException {
        User user = userRepository.getUser(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IOException("Invalid Credentials");
        };

        return user;
    }
}