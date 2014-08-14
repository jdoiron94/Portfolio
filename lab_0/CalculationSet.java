package lab_0;

import java.util.Arrays;

public class CalculationSet {

    private final int lowest;
    private final int highest;

    private final double median;
    private final double mean;

    public CalculationSet(int[] nums) {
        Arrays.sort(nums);
        lowest = nums[0];
        highest = nums[nums.length - 1];
        median = nums.length % 2 == 0 ? ((double) nums[nums.length / 2 - 1] + nums[nums.length / 2]) / 2 : nums[nums.length / 2];
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        mean = (double) sum / nums.length;
    }

    @Override
    public String toString() {
        return "Highest: " + highest + "\nLowest: " + lowest + "\nMedian: " + median + "\nMean: " + mean;
    }
}