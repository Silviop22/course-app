package al.sda.session;

import al.sda.course.Course;
import al.sda.course.CourseService;
import al.sda.user.Creator;
import al.sda.user.Student;
import al.sda.user.User;
import al.sda.user.UserService;

import java.util.Scanner;

public abstract class SessionProcessor {
    protected final CourseService courseService;
    protected final Session session;
    protected final Scanner scanner;

    public SessionProcessor(CourseService courseService, Session session, Scanner scanner) {
        this.courseService = courseService;
        this.session = session;
        this.scanner = scanner;
    }

    public void addCourse(User user, Course course) {
        user.addCourse(course);
    }


    public void process() {
        while (true) {
            System.out.println("Enter command");
            String commandString = scanner.nextLine();
            //LOG_IN

            if (commandString.equals("Log out")) {
                break;
            }

            switch (commandString) {
                case "LIST": {
                    listCourses();
                    break;
                }
                case "LIST_MY_COURSES": {
                    listMyCourses();
                    break;
                }
                case "ADD_COURSE": {
                    addCourse();
                    break;
                }
            }
        }
    }

    protected void listCourses() {
        String[] courses = courseService.listCourses();
        for (String course : courses) {
            if (course != null) {
                System.out.println(course);
            }
        }
    }

    protected abstract void addCourse();

    protected void listMyCourses() {
        for (Course course : session.getUser().getCourses()) {
            System.out.println(course.toString());
        }
    }
}
