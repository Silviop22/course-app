package al.sda.course;

import al.sda.shared.Repository;

import java.util.NoSuchElementException;

public class CourseRepository implements Repository {
    private Course[] courses = new Course[10];

    public CourseRepository() {
        Course java = new Course();
        java.setId(1);
        java.setName("Java from Scratch");
        java.setDescription("This course is about java");
        java.setDuration(40);
        java.setPrice(9.99);
        addCourse(java);

        Course python = new Course();
        python.setId(2);
        python.setName("Python");
        python.setDescription("This course is about python");
        python.setDuration(35);
        python.setPrice(9.99);
        addCourse(python);
    }

    public Course[] getCourses() {
        return courses;
    }

    public Course getCourseById(long id) {
        for (Course course : courses) {
            if (id == course.getId()) {
                return course;
            }
        }

        throw new NoSuchElementException("Could not find course");
    }

    public Course addCourse(Course course) {
        if (!hasFreeSpace()) {
            courses = getIncreasedArray();
        }

        saveCourse(course);
        return course;
    }

    private void saveCourse(Course course) {
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) {
                courses[i] = course;
                return;
            }
        }

        throw new RuntimeException("Could not find a free slot for adding the course");
    }

    private Course[] getIncreasedArray(){
        Course[] result = new Course[courses.length + 10];
        for (int i = 0; i < courses.length; i++) {
            result[i] = courses[i];
        }
        return result;
    }

    @Override
    public boolean hasFreeSpace() {
        for (Course course : courses) {
            if (course == null) {
                return true;
            }
        }
        return false;
    }
}