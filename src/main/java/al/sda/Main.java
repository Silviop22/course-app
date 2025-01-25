package al.sda;

import al.sda.course.CourseRepository;
import al.sda.course.CourseService;
import al.sda.session.CreatorProcessor;
import al.sda.session.Session;
import al.sda.session.SessionProcessor;
import al.sda.session.StudentProcessor;
import al.sda.shared.Command;
import al.sda.shared.Role;
import al.sda.shared.WrongCredentialsException;
import al.sda.user.Student;
import al.sda.user.User;
import al.sda.user.UserRepository;
import al.sda.user.UserService;

import java.io.IOException;
import java.util.NoSuchElementException;
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
            Command command;
            try {
                command = Command.valueOf(commandValue);
            } catch (IllegalArgumentException e) {
                System.out.println("Command unrecognized, try again with one of the commands below:");
                System.out.println("    - " + Command.LOG_IN);
                System.out.println("    - " + Command.SIGN_UP);
                System.out.println("    - " + Command.EXIT);
                continue;
            }

            if (command == Command.EXIT) {
                break;
            }

            System.out.println("Enter a username");
            String username = scanner.nextLine();
            System.out.println("Enter a password");
            String password = scanner.nextLine();
            switch (command) {
                case LOG_IN:
                    try {
                        User user = userService.logIn(username, password);
                        Session session = new Session(user);
                        if (user instanceof Student) {
                            processor = new StudentProcessor(courseService, session, scanner);
                        } else {
                            processor = new CreatorProcessor(courseService, session, scanner);
                        }
                        processor.process();
                    } catch (WrongCredentialsException | NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.println("Something went wrong, please contact support.");
                        System.exit(500);
                    }
                    break;
                case SIGN_UP:
                    try {
                        System.out.println("Choose role");
                        String role = scanner.nextLine();
                        Role newRole = Role.fromString(role);
                        userService.saveUser(username, password, role);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
            }
        }
    }

}