package semester_iii.other;

import java.util.Arrays;

public class Classwork {

    private static <K> K copyOne(K b) {
        return b;
    }

    @SafeVarargs
    private static <K> K[] copyTwo(K... b) {
        K[] a = (K[]) new Object[b.length];
        System.arraycopy(b, 0, a, 0, b.length);
        return a;
    }

    @SafeVarargs
    private static <K> K[] returnCopy(K... a) {
        return Arrays.copyOf(a, a.length);
    }

    public static void main(String... args) {
        Object a;
        Object b = ";-;";
        a = copyOne(b);
        System.out.println(a);
        System.out.println(Arrays.toString(copyTwo("c, d, e")));
        Object[] z = new Object[]{"a", "b", "c"};
        System.out.println(a.hashCode());
        Object[] copied = returnCopy(z);
        System.out.println(Arrays.hashCode(copied) + "\n" + Arrays.toString(copied));
    }

    /*
      What's a graph: G = (V, E)
      G: graph
      V: set of vertices/nodes
      E: set of edges/links
      directed vs undirected
      cyclic vs acyclic
      how to represent graphs?
           1: adjacency matrix: inefficient from a memory standpoint if graph is sparse
           2: adjacency list: a list of lists. Each entry in my adjacency list is a list of all edges that that vertex is connected to
      positive weighted shortest path: directed, weighted, arbitrary graph

      dijkstra's algorithm: edsger dijkstra
      the cruelty of really teaching computer science - 1988
      "testing shows the presence, not the absence, of bugs"
      "computer science is no more about computers than astronomy is about telescopes"
      "the use of cobol cripples the mind; its teaching should, therefore, be regarded as a criminal offense"
      "programming is one of the most difficult branches of applied mathematics; the poorer mathematicians had better remain pure mathematicians"

      start by initializing a distance value to every vertex
      set the source to 0, all other vertices to large number
      mark all vertices as unvisited
      loop:
           looking at the current vertex (start with source), add the current vertex's value to the edge weight of one of its neighbors
           if that neighbor has a value greater than the new value, replace
           once we've looked at all neighbors of the current vertex, mark it as visited, then move to the next vertex according to distance
           repeat until we've visited the destination vertex
     */
}