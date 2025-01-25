package al.sda.session;

import al.sda.course.Course;
import al.sda.course.CourseService;
import al.sda.user.Creator;
import al.sda.user.Student;
import al.sda.user.User;

import java.util.Scanner;

public final class StudentProcessor extends SessionProcessor{
    public StudentProcessor(CourseService courseService, Session session, Scanner scanner) {
        super(courseService, session, scanner);
    }

    @Override
    protected void addCourse() {
        User currentUser = session.getUser();
        if (currentUser.getClass() != Student.class) {
            System.out.println("Operation not allowed");
            return;
        }

        System.out.println("Enter course id");
        int id = scanner.nextInt();
        Course course = courseService.getCourse(id);

        if (((Student) currentUser).getCredit() < course.getPrice()) {
            System.out.println("Not enough credit");
        }

        addCourse(currentUser, course);
    }
}
