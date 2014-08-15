package semester_ii.enrollment;

import java.util.HashSet;
import java.util.Set;

public class TeachingAssistant extends Person {

    private Set<Course> assistedCourses;

    public TeachingAssistant(String firstName, String lastName) {
        super(firstName, lastName);
        assistedCourses = new HashSet<>(0);
    }

    public Set<Course> getAssistedCourses() {
        return assistedCourses;
    }

    public void setAssistedCourses(Set<Course> assistedCourses) {
        this.assistedCourses = assistedCourses;
    }

    public String getSimpleRepresentation() {
        return super.toString();
    }

    @Override
    public String toString() {
        return String.format("%s\nAssisted Courses: %s", super.toString(), EnrollmentTest.getSimpleCourse(assistedCourses));
    }
}