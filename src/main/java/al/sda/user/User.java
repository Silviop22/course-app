package al.sda.user;

import al.sda.course.Course;

import java.util.Arrays;
import java.util.Objects;

public class User {
    private String username;
    private String password;
    private boolean enabled;
    private Course[] courses;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Course[] getCourses() {
        if (this.courses == null) {
            this.courses = new Course[10];
        }

        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        if (!hasFreeSpace()) {
            this.courses = getIncreasedArray();
        }

        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) {
                courses[i] = course;
                return;
            }
        }

        throw new RuntimeException("Could not find a free slot for adding the course");
    }

    private Course[] getIncreasedArray() {
        return Arrays.copyOf(courses, getCourses().length + 10);
    }

    private boolean hasFreeSpace() {
        for (Course course : getCourses()) {
            if (course == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.deepEquals(courses, user.courses);
    }
}