package semester_iii.iterator_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Roster {

    private final List<String> names = new ArrayList<>();

    private int size;

    public Roster(String[] names) {
        setNames(names);
    }

    public static void main(String[] args) {
        Roster phonetic = new Roster(new String[]{"Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima", "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "Xray", "Yankee", "Zulu"});
        phonetic.print();
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names.clear();
        setSize(names.length);
        Collections.addAll(this.names, names);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void print() {
        Iterator iterator = names.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Override
    public String toString() {
        return size + ": " + Arrays.toString(names.toArray());
    }
}