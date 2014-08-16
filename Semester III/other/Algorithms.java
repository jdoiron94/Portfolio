package semester_iii.other;

public class Algorithms {

    /**
     * Linear O(N) implementation
     *
     * @param nums The sequence to query
     * @return Returns the maximum continuous sub-sequence sum
     */
    private static int maximumSubsequenceSum(int... nums) {
        int temp = 0;
        int max = 0;
        for (int n : nums) {
            temp += n;
            if (temp > max) {
                max = temp;
            } else if (temp < 0) {
                temp = 0;
            }
        }
        return max;
    }

    public static void main(String... args) {
        System.out.println(maximumSubsequenceSum(1, 2, 3, 4, 5, -10, 11));
    }

    /** c o l u m n s (dest)
      r
      o
      w
      s
      (src)
      Adjacency matrix: 2 dimensional array of integers
     */

    /**
      Insertion sort: simplest sort, suitable for small inputs
      Works by taking elements from the input, one at a time, and inserting them into the appropriate spots of a sorted array or other suitable container type
      Easiest way to deal with this: two arrays
      In-place insertion sort:
           public void insertionSort(int[] a) {
               for (int p = 1; p < a.length; p++) {
                  int tmp = a[p];
                   int j = p;
                   for (; j > 0 && temp < a[j-1]; j--) {
                       a[j] = a[j -1];
                   }
                   a[j] = tmp;
               }
           }

      Shellsort (diminishing gap sort): 1959 Donald Shell invents, notes that the data movement of insertion sort is the pricey factor
      This is the simplest of "fast" sorts
      Basic idea: Shellsort uses a sequence of integers called an increment sequence. Using this sequence (first one is always 1), we proceed in phases. After each
      phase, using some increment 'h', a[i] <= a[i+h], meaning all elements h apart are sorted
      Start with some gap value, reducing it each iteration. Gap of 1 indicates basic insertion sort over the entire array
      Shell originally proposed starting with a gap of n/2, then dividing by 2 each iteration until we get to 1
      Shell's gap gets us to O(n^3/2)
      Current best: start with a gap of n/2, then divide by 2.2 each iteration: O(n^7/6)

           public void shellsort(int[] a) {
               for (int gap = a.length / 2; gap > 0; gap = gap == 2 ? 1 : ((int) (gap / 2.2)) {
                   int tmp a[i];
                   int j = i;
                   for (; j >= gap && tmp < a[j - gap]; j -= gap) {
                       a[j] = a[j - gap];
                   }
                   a[j] = tmp;
               }
           }

      Merge sort: leverages recursion to get bounds even lower (three step process)
      1: if the number of items to sort is 0 or 1, return
      2: recursively sort the first and second halves separately
      3: merge the two sorted halves into a sorted sub-array

      Target performance: O(n log n)

      log n factor: recursively subdividing the array in half
      n factor: merge operation

      Two arrays a and b, and a destination array c. a and b are sorted
      Three counters to represent the current index in a, b and c
      Look at the elements at a[actr] and b[bctr]. Move the smaller of the two into c[cctr++]
      Increment the counter associated with the smaller element
      Repeat until actr and bctr are at the size of their respective array
      Transfer any remaining elements to c

           public void mergeSort(int[] a) {
               int[] tmparray = new int[a.length];
               mergeSort(a, tmparray, 0, a.length - 1);
           }

           public void mergeSort(int[] a, int[] tmparray, int left, int right) {
               if (left < right) {
                   int center = (left + right) / 2;
                   mergeSort(a, tmparray, left, center);
                   mergeSort(a, tmparray, center + 1, right);
                   merge(a, tmparray, left, center + 1, right);
               }
           }

           public void merge(int[] a, int[] tmparray, int leftPos, int rightPos, int rightEnd) {
               int leftEnd = rightPos - 1;
               int tmpPos = leftPos;
               int numElements = rightEnd - leftPos + 1;
               while (leftPos <= leftEnd && rightPos <= rightEnd) {
                   if (a[leftPos] < a[rightPos]) {
                       tmparray[tmpPos++] = a[leftPos++];
                   } else {
                       tmparray[tmpPos++] = a[rightPos++];
                   }
               }
               while (leftPos <= leftEnd) {
                   tmparray[tmpPos++] = a[leftPos++];
               }
               while (rightPos <= rightEnd) {
                   tmparray[tmpPos++] = a[rightPos++];
               }
               for (int i = 0; i < numElements; i++, rightEnd--) {
                   a[rightEnd] = tmparray[rightEnd];
               }
           }

      Quicksort: O(n log n), recursive, divide and conquer technique. Easily parallelizable to O(log n), most complicated to implement correctly
      Basic algorithm: S in our array
      1: if the number of elements in S is 0 or 1, return
      2: pick any element v in S, this is called the pivot
      3: partition S - {v} into two disjoint groups:
           L = {x \in S-{v}} | x <= v
           R = {x \in S-{v}} | x >= v
      4: return the result of quicksort(L) followed by v, followed by quicksort(R)

      Things we need to be concerned about:
      1: How do we select the pivot
      2: How do we do the partitioning efficiently
      3: What do we do with keys == pivot

      First, general analysis:
           best case? all sorted, we want the array to be split evenly each recursive call
           worst case? consistently partition into arrays of size 1, and array.length-1

      Worst case nets us O(n^2)
      Picking the pivot:
           first and last: bad due to natural occurrence of sorted data, O(n^2)
           middle: okay
           arbitrary: okay
           median of three: turns out, pretty good

      How to partition:
           in-place partitioning strategy:
           1: swap the pivot with the element at the end
           2: going from left to right, find an element that is larger than the pivot. this is at index i
           3: going from right to left, find an element that is smaller than pivot. this is at index k
           4: swap the element at index i with the element at index j
           5: repeat 2-4 until i > j. swap the element at the end with the element at index i

      1: let L or R take all keys equal to the pivot
      2: create a counter controlled distribution
      3: if one partition is smaller than the other, add key to that partition
      4: create a key==pivot partition
      5: traditional solution: during the partitioning strategy, just have i and j stop on keys == pivot

      final question to deal with: is this really efficient for all sized arrays? no - for small sized arrays (< 100-1000) very inefficient
      can I use quicksort in general application? yes, requires a cutoff. at some size array, we switch to insertion sort rather than continuing the recursion

      to recap:
           S is our array
           1: if the size of S <= 10, insertion sort S, then return
           2: pick the median of three elements as your pivot
           3: partition the result of quicksort(L), v, quicksort(R)
     */
}