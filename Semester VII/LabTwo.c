#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Lab completed by both Jacob Doiron and Andrew Riehl

typedef enum {
  false, true
} bool;

/*
 * This method prompts the user for three integers and stores them as pointers.
 * It then sums up the integers and outputs the result of their sum. The method
 * concludes by freeing the integer pointers.
 */
void add3Integers()
{
  int *i = (int*) malloc(sizeof(int));
  int *j = (int*) malloc(sizeof(int));
  int *k = (int*) malloc(sizeof(int));
  printf("\nadd3Integers:\nPlease enter a number: ");
  scanf("%d", i);
  printf("Please enter a number: ");
  scanf("%d", j);
  printf("Please enter a number: ");
  scanf("%d", k);
  printf("Sum: %d\n\n", *i + *j + *k);
  free(i);
  free(j);
  free(k);
}

/*
 * This method prompts the user for a number of elements to be stored in an
 * array and then asks for each number, in turn. The integers are stored
 * in an array and summed up as the user enters the numbers. It then prints
 * the sum and frees the array.
 */
void ArrayAdd()
{
  int i, size, sum = 0, *integers;
  printf("ArrayAdd:\nEnter the size of your array: ");
  scanf("%d", &size);
  integers = (int*) malloc(size * sizeof(int));
  for (i = 0; i < size; i++) {
    printf("Please enter a number: ");
    scanf("%d", &integers[i]);
    sum += integers[i];
  }
  printf("Sum: %d\n\n", sum);
  free(integers);
}

/*
 * This method recursively determines the factorial of a given number. To
 * accomplish this, it takes a pointer, allowing for no return to be necessary.
 */
/*void factorial(int index, int *result)
{
  if (index == 0 || index == 1) {
    *result = 1;
  } else {
    factorial(index - 1, result);
    *result = index **result;
  }
}*/

void factorial(int total, int n) {
  if (n == 1) {
    printf("%d", total);
  } else {
    factorial(total * n, n - 1);
  }
}

/*
 * This helper method takes a string and reverses its contents.
 */
char *reverse(char text[])
{
  int i, j = 0, length = strlen(text);
  char *reverse = malloc(length * sizeof(char));
  for (i = length - 1; i >= 0; i--, j++) {
    reverse[j] = text[i];
  }
  return reverse;
}

/*
 * This method takes a string and determines if it is a palindrome -
 * i.e. the same characters backward as it is forward. It does this by reversing
 * the original string and then comparing the reverse to the original and checks
 * the return status, which tells us if it is the same or not.
 */
bool palindrome(char text[])
{
  return strcmp(text, reverse(text)) == 0 ? true : false;
}

/*
 * This method takes the height of a pyramid and then starts to draw a pyramid
 * whose height is n and whose base has n stars. The first line of the pyramid
 * will have n spaces before its asterisk. Each subsequent line will have n-1
 * spaces before the first asterisk. Each asterisk will be separated by one
 * space.
 */
void pyramid(int n)
{
  int i, j, k, remaining = n;
  for (i = 1; i <= n; i++) {
    for (j = 1; j <= remaining; j++) {
      printf(" ");
    }
    for (k = 1; k <= i; k++) {
      printf("* ");
    }
    printf("\n");
    remaining--;
  }
}

/*
 * This method takes a decimal integer and converts it to its binary
 * representation as a string. It does so by first allocating an array which
 * can hold up to 32 bits of information (an integer's max value). Then, it
 * loops. Each loop, it makes sure there are still significant bits
 * (i.e. not 0). It checks to see if the present 'number' value has a 1 in the
 * rightmost bit. If it does, it appends a 1; otherwise, it appends a 0. Then,
 * it shifts the bits right and continues on in this fashion. At the end, it
 * simply reverses the array (since it was building it backward) and returns its
 * result, the decimal integer converted into its binary string equivalent.
 */
char *intToBinary(int number)
{
  char *bits = malloc(32 * sizeof(char));
  int i = 0;
  while (number != 0) {
    bits[i++] = number & 1 == 1 ? '1' : '0';
    number >>= 1;
  }
  return reverse(bits);
}

/*
 * This is the main method which will test all of the required functions, as
 * specified by lab two.
 */
int main(int argc, char **argv)
{
  int *result = (int*) malloc(sizeof(int));
  *result = 1;
  add3Integers();
  ArrayAdd();
  factorial(5, result);
  printf("factorial:\nFactorial (5! = 120): %d\n\n", *result);
  free(result);
  printf("palindrome:\n'radar': %s",
    palindrome("radar") ? "It's a palindrome\n" : "It is not a palindrome\n");
  printf("'123': %s",
    palindrome("123") ? "It's a palindrome\n" : "It is not a palindrome\n");
  printf("\npyramid:\n");
  pyramid(5);
  printf("\nintToBinary:\n");
  printf("1337 in binary: %s", intToBinary(1337));
  return 0;
}
