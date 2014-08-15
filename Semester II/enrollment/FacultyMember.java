package semester_ii.enrollment;

import java.util.HashSet;
import java.util.Set;

public class FacultyMember extends Person {

    private Set<Course> coursesTaught;

    public FacultyMember(String firstName, String lastName) {
        super(firstName, lastName);
        coursesTaught = new HashSet<>(0);
    }

    public Set<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public void setCoursesTaught(Set<Course> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }

    public String getSimpleRepresentation() {
        return super.toString();
    }

    @Override
    public String toString() {
        return String.format("%s\nCourses Taught: %s", super.toString(), EnrollmentTest.getSimpleCourse(coursesTaught));
    }
}