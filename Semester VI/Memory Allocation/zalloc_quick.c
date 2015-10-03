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

static HeaderPtr sm;
static HeaderPtr md;
static HeaderPtr lg;
static HeaderPtr xl;

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
  HeaderPtr block = start -> next;
  while (NULL != block) {
    sort(block);
    block = block -> next;
  }
}

/**
  Method which will append the specified block to the end of a given list
 */
void add(HeaderPtr block, HeaderPtr list)
{
  while (NULL != list) {
    list = list -> next;
  }
  list -> next = block;
}

/**
  Method which will sort the specified block into one of five lists: 8 blocks, 16, 32, or 64
 */
int sort(HeaderPtr block)
{
  if (NULL == block || block -> size > 64) {
    return 0;
  }
  switch (block -> size) {
    case 8:
      add(block, sm);
      break;
    case 16:
      add(block, md);
      break;
    case 32:
      add(block, lg);
      break;
    case 64:
      add(block, xl);
      break;
  }
  return 1;
}

/**
  Method which takes a block size to allocate and finds where to get it from. If it is a size recognized
  by one of the 8, 16, 32, or 64 block quick lists, it will simply grab the block from there and use it;
  if not, it will perform a best-fit search and locate the best block to use with that method.
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

  if (size <= 64) {
    switch (block -> size) {
      case 8:
        block = sm;
        break;
      case 16:
        block = md;
        break;
      case 32:
        block = lg;
        break;
      case 64:
        block = xl;
        break;
    }
  } else {
    // search for > 64 size block
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
  }
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
  Frees the given block and then properly sorts it into one of the four quick lists, or the standard list
 */
void zfree(void* p)
{
  HeaderPtr actual = p - sizeof(size_t);
  if (sort(actual) == 0) {
    actual -> next = start -> next;
    start -> next = actual;
  }
}
