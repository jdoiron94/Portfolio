package semester_ii.enrollment;

import java.util.HashSet;
import java.util.Set;

public class Student extends Person {

    private Set<Course> courses;

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
        courses = new HashSet<>(5);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        course.add(this);
        courses.add(course);
    }

    public void dropCourse(Course course) {
        course.drop(this);
        courses.remove(course);
    }

    @Override
    public String getSimpleRepresentation() {
        return super.toString();
    }

    @Override
    public String toString() {
        return String.format("%s\nCourses: %s", super.toString(), EnrollmentTest.getSimpleCourse(courses));
    }
}