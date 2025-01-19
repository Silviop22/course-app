package al.sda;

import al.sda.course.CourseRepository;
import al.sda.course.CourseService;
import al.sda.session.CreatorProcessor;
import al.sda.session.Session;
import al.sda.session.SessionProcessor;
import al.sda.session.StudentProcessor;
import al.sda.shared.Command;
import al.sda.shared.NotAllowedOperationException;
import al.sda.shared.Role;
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
            Command command;
            try {
                command = Command.valueOf(commandValue);
                if (command != Command.LOG_IN || command != Command.SIGN_UP
                        || command != Command.LOG_OUT) {
                    throw new NotAllowedOperationException("Command not allowed");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Unrecognized command, possible commands are:");
                for (Command c : Command.values()) {
                    System.out.println("  - " + c);
                }
                continue;
            } catch (NotAllowedOperationException e) {
                System.out.println(e.getMessage());
                System.out.println("Only " + Command.LOG_IN + ", " + Command.SIGN_UP + ", " + Command.LOG_OUT + " allowed.");
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
                        if (user.getClass() == Student.class) {
                            processor = new StudentProcessor(courseService);
                        } else {
                            processor = new CreatorProcessor(courseService);
                        }
                        processor.process(session, scanner);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case SIGN_UP:
                    System.out.println("Enter role");
                    String roleValue = scanner.nextLine();
                    Role role = Role.valueOf(roleValue);
                    userService.saveUser(username, password, role);
                    break;
            }
        }
    }

}