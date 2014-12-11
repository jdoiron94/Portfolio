package jmetal.problems;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.util.JMException;

/**
 * {@author Jacob Doiron, Jenna Raleigh}
 * Class which represents the multi-objective Test Data Generation Problem for use with
 * {@link #triang(double, double, double)} as the selected program to maximize branch coverage and minimize the test
 * suite size.
 */
public class TDGMulti extends Problem {

    // Global boolean array which is used to keep track of which branches have been visited
    private boolean[] branches;

    /**
     * Constructor which sets the variables in terms of the problem defined
     */
    public TDGMulti() {
        numberOfVariables_ = 300;
        numberOfObjectives_ = 2;
        numberOfConstraints_ = 0;
        problemName_ = "TDG";
        solutionType_ = new RealSolutionType(this);
        upperLimit_ = new double[numberOfVariables_];
        lowerLimit_ = new double[numberOfVariables_];
        for (int i = 0; i < numberOfVariables_; i++) {
            lowerLimit_[i] = 0.0;
            upperLimit_[i] = 100.0;
        }
    }

    /**
     * Method which evaluates the branch coverage of the test vector. It starts by initializing the boolean array, and
     * then it iterates through the vector and uses each set of three sides to run through
     * {@link #triang(double, double, double)}. It then sets the first objective as the number of branches covered out
     * of the total amount of branches. To minimize the size of the test suite, it checks for a sentinel value.
     * @param solution The {@code Solution} to evaluate.
     * @throws JMException
     */
    @Override
    public void evaluate(Solution solution) throws JMException {
        branches = new boolean[12];
        int covered = 0;
        int x = 0;
        Variable[] decisionVariables = solution.getDecisionVariables();
        for (int i = 0; i < numberOfVariables_; i += 3) {
            double s = triang(Math.round(decisionVariables[i].getValue()), Math.round(decisionVariables[i + 1].getValue()), Math.round(decisionVariables[i + 2].getValue()));
            if (s == 6000.0) {
                x++;
            }
        }
        for (boolean b : branches) {
            if (b) {
                covered++;
            }
        }
        solution.setObjective(0, 1 - (double) covered / 12);
        solution.setObjective(1, 100 - x);
    }

    /**
     * The branch evaluation method which is being used to determine which branches are covered, given certain test
     * data. The method itself determines what type of triangle is being represented, depending on the lengths of the
     * sides given as parameters. Should any of the sides of the triangle be 0, a sentinel value is returned.
     * @param x the first side of the triangle
     * @param x2 the second side of the triangle
     * @param x3 the third side of the triangle
     * @return the type of triangle represented, according to its side lengths
     */
    private double triang(double x, double x2, double x3) {
        if (x <= 0 || x2 <= 0 || x3 <= 0) {
            branches[0] = true;
            return 6000.0;
        }
        //check for equal length sides
        int tri = 0;
        if (x == x2) {
            branches[1] = true;
            tri = tri + 1;
        }
        if (x == x3) {
            branches[2] = true;
            tri = tri + 2;
        }
        if (x2 == x3) {
            branches[3] = true;
            tri = tri + 3;
        }
        if (tri == 0) {
            //if there are no equal sides
            branches[4] = true;
            if (x + x2 <= x3 || x2 + x3 <= x || x + x3 <= x2) {
                //triangle is invalid
                branches[5] = true;
                tri = 4;
            } else {
                //triangle is scalene
                branches[6] = true;
                tri = 1;
            }
            return tri;
        }
        if (tri > 3) {
            //triangle is equilateral
            branches[7] = true;
            tri = 3;
        } else if (tri == 1 && x + x2 > x3) {
            //triangle is isosceles (i==j)
            branches[8] = true;
            tri = 2;
        } else if (tri == 2 && x + x3 > x2) {
            //triangle is isosceles (i==k)
            branches[9] = true;
            tri = 2;
        } else if (tri == 3 && x2 + x3 > x) {
            //triangle is isosceles (j==k)
            branches[10] = true;
            tri = 2;
        } else {
            //triangle is invalid
            branches[11] = true;
            tri = 4;
        }
        return tri;
    }
}