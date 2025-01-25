package al.sda.session;

import al.sda.course.Course;
import al.sda.course.CourseService;
import al.sda.user.Creator;
import al.sda.user.Student;
import al.sda.user.User;
import al.sda.user.UserService;

import java.util.Scanner;

public final class CreatorProcessor extends SessionProcessor {
    public CreatorProcessor(CourseService courseService, Session session, Scanner scanner) {
        super(courseService, session, scanner);
    }


    @Override
    protected void addCourse() {
        User currentUser = session.getUser();
        if (currentUser.getClass() != Creator.class) {
            System.out.println("Operation not allowed");
            return;
        }

        if (((Creator) currentUser).getCertification() == null) {
            System.out.println("You need a certification to create a course");
            return;
        }

        System.out.println("Add course name");
        String name = scanner.nextLine();

        System.out.println("Add description");
        String description = scanner.nextLine();

        System.out.println("Add price");
        double price = scanner.nextDouble();

        System.out.println("Add duration in hours");
        int duration = scanner.nextInt();

        Course addedCourse = courseService.addCourse(name, description, price, duration);
        addCourse(currentUser, addedCourse);
    }
}
