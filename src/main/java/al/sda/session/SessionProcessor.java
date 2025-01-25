package al.sda.session;

import al.sda.course.Course;
import al.sda.course.CourseService;
import al.sda.user.User;

import java.util.Scanner;

public class SessionProcessor {
    private final CourseService courseService;

    public SessionProcessor(CourseService courseService) {
        this.courseService = courseService;
    }

    public void addCourse(User user, Course course) {
        user.addCourse(course);
    }


    public void process(Session session, Scanner scanner) {
        while (true) {
            System.out.println("Enter command");
            String commandString = scanner.nextLine();
            //LOG_IN

            if (commandString.equals("Log out")) {
                break;
            }

            switch (commandString) {
                case "LIST": {
                    String[] courses = courseService.listCourses();
                    for (String course : courses) {
                        if (course != null) {
                            System.out.println(course);
                        }
                    }
                    break;
                }
                case "BUY": {
                    System.out.println("Enter course id");
                    int id = scanner.nextInt();
                    Course course = courseService.getCourse(id);
                    addCourse(session.getUser(), course);
                    break;
                }
                case "LIST_MY_COURSES": {
                    for (Course course : session.getUser().getCourses()) {
                        System.out.println(course.toString());
                    }
                    break;
                }
            }

        }
    }
}
