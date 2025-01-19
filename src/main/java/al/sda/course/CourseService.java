package al.sda.course;

public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public String[] listCourses() {
        Course[] courses = repository.getCourses();
        String[] result = new String[courses.length];

        for (int i = 0; i < courses.length; i++) {
            if (courses[i] != null) {
                result[i] = courses[i].toString();
            }
        }

        return result;
    }

    public Course getCourse(long id) {
        return repository.getCourseById(id);
    }
}