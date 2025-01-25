package al.sda;

import al.sda.course.CourseRepository;
import al.sda.course.CourseService;
import al.sda.session.CreatorProcessor;
import al.sda.session.Session;
import al.sda.session.SessionProcessor;
import al.sda.session.StudentProcessor;
import al.sda.user.Student;
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

        SessionProcessor processor;
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
                    User user = userService.logIn(username, password);
                    Session session = new Session(user);
                    if (user instanceof Student) {
                        processor = new StudentProcessor(courseService, session, scanner);
                    } else {
                        processor = new CreatorProcessor(courseService, session, scanner);
                    }
                    processor.process();
                    break;
                case "SIGN_UP":
                    System.out.println("Choose role");
                    String role = scanner.nextLine();
                    userService.saveUser(username, password, role);
                    break;
            }
        }
    }

}