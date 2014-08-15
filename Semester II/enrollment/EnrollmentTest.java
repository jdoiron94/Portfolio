package semester_ii.enrollment;

import java.util.HashSet;
import java.util.Set;

public class EnrollmentTest {

    /**
     * I'll forewarn you now, this is one of the absolute worst designs possible.
     * It was made according to the specifications, however.
     * Pardon the fact that there are only two students and two courses,
     * I couldn't be bothered getting more creative when it takes so many lines
     * to essentially write something which could have otherwise been written
     * using arrays in about half of the time it took to do it using Sets.
     */

    protected static String getSimplePerson(Set<? extends Person> people) {
        String simple = "[";
        for (Person p : people) {
            simple += p.getSimpleRepresentation() + ", ";
        }
        return (simple.length() > 2 ? simple.substring(0, simple.length() - 2) : simple) + "]";
    }

    protected static String getSimpleCourse(Set<? extends Course> courses) {
        String simple = "[";
        for (Course c : courses) {
            simple += c.getName() + ", ";
        }
        return (simple.length() > 2 ? simple.substring(0, simple.length() - 2) : simple) + "]";
    }

    public static void main(String[] args) {
        Person person = new Person("Jacob", "Doiron");
        System.out.println("Person: " + person + "\n");
        Course compSci = new Course("Comp Sci II");
        FacultyMember simon = new FacultyMember("Simon", "Read");
        Set<Course> simonsCourses = new HashSet<>(1);
        simonsCourses.add(compSci);
        simon.setCoursesTaught(simonsCourses);
        System.out.println(simon + "\n");
        TeachingAssistant mark = new TeachingAssistant("Mark", "Lastname");
        Set<Course> marksAssistedCourses = new HashSet<>(1);
        marksAssistedCourses.add(compSci);
        mark.setAssistedCourses(marksAssistedCourses);
        System.out.println(mark + "\n");
        TeachingAssistant ryan = new TeachingAssistant("Ryan", "Lastname");
        Set<Course> ryansAssistedCourses = new HashSet<>(1);
        ryansAssistedCourses.add(compSci);
        ryan.setAssistedCourses(ryansAssistedCourses);
        System.out.println(ryan + "\n");
        Set<TeachingAssistant> compSciAssistants = new HashSet<>(1);
        compSciAssistants.add(mark);
        compSciAssistants.add(ryan);
        Set<Student> compSciStudents = new HashSet<>(2);
        compSci.setFacultyMember(simon);
        compSci.setAssistants(compSciAssistants);
        compSci.setStudents(compSciStudents);
        Course lit = new Course("Literature");
        FacultyMember robin = new FacultyMember("Robin", "Bates");
        Set<Course> robinsCourses = new HashSet<>(1);
        robinsCourses.add(lit);
        robin.setCoursesTaught(robinsCourses);
        Set<Student> litStudents = new HashSet<>(2);
        lit.setFacultyMember(robin);
        lit.setStudents(litStudents);
        Set<Course> mutual = new HashSet<>(2);
        mutual.add(lit);
        mutual.add(compSci);
        Student jacob = new Student("Jacob", "Doiron");
        jacob.setCourses(mutual);
        Student tyler = new Student("Tyler", "Sedlar");
        tyler.setCourses(mutual);
        compSciStudents.add(jacob);
        compSciStudents.add(tyler);
        litStudents.add(jacob);
        litStudents.add(tyler);
        System.out.println(jacob + "\n\n" + tyler + "\n\n" + compSci + "\n\n" + lit);
        jacob.dropCourse(lit);
        System.out.println("\nJacob dropping Lit:\n" + jacob + "\n\n" + lit);
        jacob.addCourse(lit);
        System.out.println("\nJacob adding Lit:\n" + jacob + "\n\n" + lit);
        jacob.addCourse(compSci);
        System.out.println("\nJacob can't re-add Comp Sci when it already exists:\n" + jacob + "\n\n" + compSci);
    }
}