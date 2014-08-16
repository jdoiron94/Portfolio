package pokemon.daycare;

public class Pokemon implements Comparable<Pokemon> {

    private final String name;
    private final Relation relation;

    public Pokemon(String name, Relation relation) {
        this.name = name;
        this.relation = relation;
    }

    public String getName() {
        return name;
    }

    public Relation getRelation() {
        return relation;
    }

    @Override
    public String toString() {
        return "new Pokemon(\"" + name + "\", " + relation.getName() + ")";
    }

    @Override
    public int compareTo(Pokemon o) {
        return name.compareTo(o.getName());
    }
}