package al.sda.session;

import al.sda.course.Course;
import al.sda.course.CourseService;
import al.sda.user.Creator;
import al.sda.user.User;

public class CreatorProcessor extends SessionProcessor{
    public CreatorProcessor(CourseService courseService) {
        super(courseService);
    }

    public void addCourse(User user, Course course) {
        if (user.getClass() != Creator.class) {
            throw new RuntimeException("Not my responsability");
        }

        Creator creator = (Creator) user;
        if (creator.getCertification() == null) {
            throw new RuntimeException("Not qualified");
        }

        creator.addCourse(course);
    }
}
