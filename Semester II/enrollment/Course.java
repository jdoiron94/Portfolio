package semester_ii.enrollment;

import java.util.HashSet;
import java.util.Set;

public class Course {

    private final String name;
    private FacultyMember facultyMember;
    private Set<Student> students;
    private Set<TeachingAssistant> assistants;

    public Course(String name) {
        this.name = name;
        facultyMember = new FacultyMember("Not", "Applicable");
        students = new HashSet<>(20);
        assistants = new HashSet<>(5);
    }

    public String getName() {
        return name;
    }

    public FacultyMember getFacultyMember() {
        return facultyMember;
    }

    public void setFacultyMember(FacultyMember facultyMember) {
        this.facultyMember = facultyMember;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<TeachingAssistant> getAssistants() {
        return assistants;
    }

    public void setAssistants(Set<TeachingAssistant> assistants) {
        this.assistants = assistants;
    }

    protected void add(Student student) {
        students.add(student);
    }

    protected void drop(Student student) {
        students.remove(student);
    }

    @Override
    public String toString() {
        return String.format("Course: %s\nTeacher: %s\nStudents: %s\nAssistants: %s", name, facultyMember.getSimpleRepresentation(), EnrollmentTest.getSimplePerson(students), EnrollmentTest.getSimplePerson(assistants));
    }
}