#include <stdio.h>
#include <stdlib.h>

// Completed by Andrew Riehl and Jacob Doiron

/* Creates an integer pointer, sets the value to which it points to
 * 3, adds 2 to this value, and prints said value.
 *
 * This didn't work because the integer pointer did not create a
 * space in memory to store a value prior to declaration. Additionally, printf
 * was not using actual quotations - thanks, mr. sneaky. Added free statement
 * to clear memory as well.
 */
void test1() {
  int *a = (int*) malloc(sizeof(int));
  *a = 3;
  *a = *a + 2;
  printf("%d\n", *a);
  free(a);
}

/* Creates two integer pointers and sets the value to which they
 * point to 2 and 3, respectively.
 *
 * Cannot create multiple integer pointers at once, so split separately.
 * Added printf to ensure not out of memory and that values are properly set.
 * Changed sneaky quotations in printf. Freed pointer memory.
 */
void test2() {
  int *a = (int*) malloc(sizeof(int));
  int *b = (int*) malloc(sizeof(int));
  if(!(a && b)) {
    printf("Out of memory!");
    exit(-1);
  } else {
    printf("Good job\n");
  }
  *a = 2;
  *b = 3;
  printf("a: %d, b: %d\n", *a, *b);
  free(a);
  free(b);
}

/* Creates a 3 x 100 two-dimensional array, and sets element (1,1)
 * to 5.
 *
 * It created one dimension of the array. Created the second axis of the array
 * and then set [1][1] to 5. After that, we freed each individual pointer and
 * then the array structure itself.
 */
void test3() {
  int **a = (int**) malloc(3 * sizeof(int*));
  int i = 0;
  for (i = 0; i < 3; i++) {
    a[i] = (int*) malloc(100 * sizeof(int));
  }
  a[1][1] = 5;
  printf("a[1][1]: %d\n", a[1][1]);
  for (i = 0; i < 3; i++) {
    free(a[i]);
  }
  free(a);
}

/* Sets the value pointed to by a to an input, checks if the value
 * pointed to by a is 0, and prints a message if it is
 *
 * Set 'a' to be a pointer. Added print statement to show when console awaits
 * user input. Added != 0 print out statement and freed pointer memory.
*/
void test5(){
  int *a = (int*) malloc(sizeof(int));
  printf("Enter a value: ");
  scanf("%d", a);
  if (!a) {
    printf("Value is 0\n");
  } else {
    printf("Value is not 0\n");
  }
  free(a);
}

// Tests all test functions.
int main(int argc, char **argv) {
  test1();
  test2();
  test3();
  test5();
  return 0;
}
