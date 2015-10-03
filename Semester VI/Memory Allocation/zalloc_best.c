#include "zalloc.h"
#include <stdio.h>

#define MINIMUM_TRANCHE_SIZE (4*1024)
#define MINIMUM_USEFUL_SIZE sizeof(Header)
#define MAX(A, B) (A)>(B)?(A):(B)

typedef struct header* HeaderPtr;

typedef struct header {
  size_t size;
  HeaderPtr next;
} Header;

static Header start_block;
static HeaderPtr start;

/**
  Initiates the list with a memory allocation and then sorts the blocks into their proper quick lists
 */
void zinit(void)
{
  HeaderPtr first;
  start = &start_block;
  start -> size = 0;
  start -> next = malloc(MINIMUM_TRANCHE_SIZE);
  first = start -> next;
  first -> size = MINIMUM_TRANCHE_SIZE - sizeof(size_t);
  first -> next = NULL;
}

/**
  Method which takes a block size to allocate and finds where to get it from. It tries to find a block
  that is just large enough to house the memory allocation, so as to not waste space. However, in doing so,
  it will be creating very small slices of memory that will likely become unusable, due to their small size.
 */
void* zalloc(size_t size)
{

  HeaderPtr previous = start;
  HeaderPtr block = previous -> next;
  HeaderPtr bestBlock = block;
  HeaderPtr previousBest;
  HeaderPtr newBlock;

  size_t new_size;
  bestBlock -> size = 2147483647;

  while (NULL != block) {
    if (block -> size > size && block -> size < bestBlock -> size) {
      previousBest = previous;
      bestBlock = block;
    }
    previous = block;
    block = block -> next;
  }
  block = bestBlock;
  previous = previousBest;
  if (NULL != block) {
    if (block -> size > size){
      newBlock = (HeaderPtr) ((char*) block + sizeof(size_t) + size);
      newBlock -> size = block -> size - (sizeof(size_t) + size);
      newBlock -> next = block -> next;
      block -> size = size;
      block -> next = newBlock;
    }
    previous -> next = block -> next;
  } else {
    new_size = MAX(MINIMUM_TRANCHE_SIZE, size + sizeof(size_t));
    newBlock = malloc(new_size);
    newBlock -> size = new_size - sizeof(size_t);
    newBlock -> next = start -> next;
    start -> next = newBlock;
    block = (void*) ((char*) zalloc(size) - sizeof(size_t));
  }
  return (HeaderPtr) ((void *) block + sizeof(size_t));
}

/**
  Frees the given block and then stores it back into the standard list of free memory
 */
void zfree(void* p)
{
  HeaderPtr actual = p - sizeof(size_t);
  actual -> next = start -> next;
  start -> next = actual;
}
