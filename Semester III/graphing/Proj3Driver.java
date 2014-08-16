package semester_iii.graphing;

// Proj3Driver.java
// Alan C. Jamieson
// COSC 201 - Fall 2013
// Last Revision: November 18th, 2013

// Testing driver for Assignment 3 - Shortest Path Algorithms
// Name of target class: GraphAlgorithms
// Name of target method: processGraph()

// testing - for all source and destinations, we assume that the graph starts
// at vertex 0. Graph entries are formatted row to column. Basically, a nonzero
// entry at row n and column m means that there exists a directed edge between
// n and m with cost based on the value in the entry.

public class Proj3Driver {

    public static void main(String... args) {
        GraphAlgorithms g = new GraphAlgorithms();
        int smallValid[][] = {{0, 3},
                {2, 0}};
        int bigValid[][] = {{0, 0, 4, 0, 0, 0, 6, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 3, 0, 0, 0, 6, 1, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                {0, 0, 2, 0, 0, 0, 0, 4, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 9, 0},
                {0, 0, 0, 0, -1, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, -2},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 10, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 12, 0, 0, 13, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 0},
                {0, 0, 0, 0, 5, 0, 0, 0, 0, -2, 5, 0, 0, 0, 0, 0, 0, 0, 4, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 5, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, -3, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 5},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        //vertex 20 is disconnected
        int discon[][] = {{0, 0, 4, 0, 0, 0, 6, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 3, 0, 0, 0, 6, 1, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0},
                {0, 0, 2, 0, 0, 0, 0, 4, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 9, 0, 0},
                {0, 0, 0, 0, -1, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, -2, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 10, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 0, 0, 13, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 0, 0},
                {0, 0, 0, 0, 5, 0, 0, 0, 0, -2, 5, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 5, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, -3, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 4, 0, 0, 0, 0, 3, 0, -8, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0}};

        //edge weight between 11 and 15 causes a negative cost cycle from
        //11 to 15 to 12 to 11
        int negCycle[][] = {{0, 0, 4, 0, 0, 0, 6, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 3, 0, 0, 0, 6, 1, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                {0, 0, 2, 0, 0, 0, 0, 4, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 9, 0},
                {0, 0, 0, 0, -1, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, -2},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, -10, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 12, 0, 0, 13, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 0},
                {0, 0, 0, 0, 5, 0, 0, 0, 0, -2, 5, 0, 0, 0, 0, 0, 0, 0, 4, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 5, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, -3, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 5},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        //Valid test 1 - source and destination the same
        //Expected Output: 0 and 0
        System.out.println(g.processGraph(smallValid, 1, 0, 0));
        System.out.println(g.processGraph(smallValid, 2, 0, 0));

        //Valid test 2 - source and destination one hop away (2 vertex graph)
        //Also checking for directed graph handling
        //Expected Output: 3 and 2
        System.out.println(g.processGraph(smallValid, 1, 0, 1));
        System.out.println(g.processGraph(smallValid, 2, 1, 0));

        //Valid test 3 - regularly sized test
        //Expected Output: 14 and 14
        System.out.println(g.processGraph(bigValid, 1, 0, 17));
        System.out.println(g.processGraph(bigValid, 2, 0, 17));

        //Invalid test 1 - presence of a negative cost cycle
        //Expected Output: some negative cost cycle error message, twice
        System.out.println(g.processGraph(negCycle, 1, 0, 17));
        System.out.println(g.processGraph(negCycle, 2, 0, 17));

        //Invalid test 2 - no path present from s to d, disconnect in graph
        //Expected Output: some no path present error message, twice
        System.out.println(g.processGraph(discon, 1, 0, 20));
        System.out.println(g.processGraph(discon, 2, 0, 20));

        //Invalid test 3 - source vertex does not exist
        //Expected Output: source vertex does not exist error message, twice
        System.out.println(g.processGraph(bigValid, 1, 22, 17));
        System.out.println(g.processGraph(bigValid, 2, 22, 17));

        //Invalid test 4 - destination vertex does not exist
        //Expected Output: destination vertex does not exist error message, twice
        System.out.println(g.processGraph(bigValid, 1, 0, 117));
        System.out.println(g.processGraph(bigValid, 2, 0, 117));

    }
}