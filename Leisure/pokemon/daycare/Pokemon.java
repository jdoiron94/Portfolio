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
    public int compareTo(Pokemon o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Pokemon) {
            Pokemon p = (Pokemon) o;
            return name.equals(p.name) && relation == p.relation;
        }
        return false;
    }

    @Override
    public String toString() {
        return "new Pokemon(\"" + name + "\", " + relation.getName() + ")";
    }
}