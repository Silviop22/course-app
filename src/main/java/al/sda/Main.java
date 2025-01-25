package al.sda;

import al.sda.course.CourseRepository;
import al.sda.course.CourseService;
import al.sda.session.Session;
import al.sda.session.SessionProcessor;
import al.sda.user.User;
import al.sda.user.UserRepository;
import al.sda.user.UserService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        CourseRepository courseRepository = new CourseRepository();
        CourseService courseService = new CourseService(courseRepository);
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);

        SessionProcessor processor = new SessionProcessor(courseService);
        while (true) {
            System.out.println("Choose how to log in:");
            String commandValue = scanner.nextLine();

            if (commandValue.equals("EXIT")) {
                break;
            }

            System.out.println("Enter a username");
            String username = scanner.nextLine();
            System.out.println("Enter a password");
            String password = scanner.nextLine();
            switch (commandValue) {
                case "LOG_IN":
                    try {
                        User user = userService.logIn(username, password);
                        Session session = new Session(user);
                        processor.process(session, scanner);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "SIGN_UP":
                    userService.saveUser(username, password);
                    break;
            }
        }
    }

}