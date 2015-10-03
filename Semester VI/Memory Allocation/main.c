#include <stdio.h>
#include "zalloc.h"

int main(int argc, char** argv) {
  void* first;
  void* second;
  void* third;

  printf("The size of size_t is %lu.\n", sizeof(size_t));
  printf("The size of long is %lu.\n", sizeof(long));
  printf("The size of void* is %lu.\n", sizeof(void*));

  printf("Initializing the zalloc library.\n");
  zinit();

  printf("First allocation.\n");
  first = zalloc(100);

  printf("The first block of memory allocated is the 100 bytes starting at %p.\n", first);

  printf("Second allocation.\n");
  second = zalloc(100);

  printf("The second block of memory allocated is the 100 bytes starting at %p.\n", second);

  printf("The difference between allocated blocks is %lu.\n", second - first);

  printf("Third allocation.\n");
  third = zalloc(100);

  printf("The third block of memory allocated is the 100 bytes starting at %p.\n", third);

  printf("The difference between allocated blocks is %lu.\n", third - second);

  zfree(second);

  return EXIT_SUCCESS;

}
