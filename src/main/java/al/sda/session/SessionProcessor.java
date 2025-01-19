package al.sda.session;

import al.sda.course.Course;
import al.sda.course.CourseService;
import al.sda.shared.Command;
import al.sda.user.Creator;
import al.sda.user.User;
import al.sda.user.UserService;

import java.util.Scanner;

public abstract class SessionProcessor {
    private final CourseService courseService;

    public SessionProcessor(CourseService courseService) {
        this.courseService = courseService;
    }


    public abstract void addCourse(User user, Course course);


    public void process(Session session, Scanner scanner) {
        while (true) {
            System.out.println("Enter command");
            String commandString = scanner.nextLine();
            //LOG_IN
            Command command = Command.valueOf(commandString);
            if (command.equals("Log out")) {
                break;
            }

            switch (command) {
                case LIST: {
                    String[] courses = courseService.listCourses();
                    for (String course : courses) {
                        if (course != null) {
                            System.out.println(course);
                        }
                    }
                    break;
                }
                case BUY: {
                    System.out.println("Enter course id");
                    int id = scanner.nextInt();
                    Course course = courseService.getCourse(id);
                    addCourse(session.getUser(), course);
                    break;
                }
                case LIST_MY_COURSES: {
                    for (Course course : session.getUser().getCourses()) {
                        System.out.println(course.toString());
                    }
                    break;
                }
                case CREATE: {
                    if (session.getUser().getClass() != Creator.class) {
                        System.out.println("Operation not allowed");
                        break;
                    }
                    //addCourse(session.getUser(), );
                }
            }

        }
    }
}
