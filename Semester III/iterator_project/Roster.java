package semester_iii.iterator_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Roster {

    private int size;

    private final List<String> names = new ArrayList<>(20);

    public Roster(String... names) {
        setNames(names);
    }

    public static void main(String... args) {
        Roster phonetic = new Roster("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima", "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "Xray", "Yankee", "Zulu");
        phonetic.print();
    }

    public List<String> getNames() {
        return names;
    }

    public int getSize() {
        return size;
    }

    public void setNames(String... names) {
        this.names.clear();
        size = names.length;
        Collections.addAll(this.names, names);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void print() {
        for (String name : names) {
            System.out.println(name);
        }
    }

    @Override
    public String toString() {
        return size + ": " + Arrays.toString(names.toArray());
    }
}