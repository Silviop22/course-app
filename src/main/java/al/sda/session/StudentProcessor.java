package al.sda.session;

import al.sda.course.Course;
import al.sda.course.CourseService;
import al.sda.user.Student;
import al.sda.user.User;

public class StudentProcessor extends SessionProcessor {
    public StudentProcessor(CourseService courseService) {
        super(courseService);
    }

    @Override
    public void addCourse(User user, Course course) {
        if (user.getClass() != Student.class) {
            throw new RuntimeException("Not responsible");
        }
        Student student = (Student) user;
        if (student.getCredit() < course.getPrice()) {
            throw new RuntimeException("Not enough credit");
        }

        student.addCourse(course);
    }
}
