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

    public Course addCourse(String name, String description, double price, int duration) {
        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        course.setPrice(price);
        course.setDuration(duration);
        return repository.addCourse(course);
    }
}