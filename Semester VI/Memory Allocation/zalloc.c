#include "zalloc.h"
#include <stdio.h>

/* The smallest block that will be requested from the real malloc */
#define MINIMUM_TRANCHE_SIZE (4*1024)

/* The smallest block that cane be usefully created */
#define MINIMUM_USEFUL_SIZE sizeof(Header)

#define MAX(A, B) (A)>(B)?(A):(B)

/* The structure that will be used as the header of each free block. */

typedef struct header* HeaderPtr;

typedef struct header {
  size_t size;
  HeaderPtr next;
} Header;

/* The global start of the free list. */

static Header start_block;
static HeaderPtr start;

/* zinit: Initialise the system. */
void zinit(void)
{
  HeaderPtr first;

  start = &start_block;

  /* Initialise the start block */
  start->size = 0;
  start->next = malloc(MINIMUM_TRANCHE_SIZE);

  /* Initialise the tranche of memory */
  first = start->next;
  first->size = MINIMUM_TRANCHE_SIZE - sizeof(size_t);
  first->next = NULL;

}

/* zalloc: Allocate free memory if available. */
void* zalloc(size_t size)
{
  HeaderPtr this; /* The block to be allocated */
  HeaderPtr previous; /* HeaderPtr to the last block checked */
  HeaderPtr new_block; /* HeaderPtr to a new fragment block */
  size_t new_size; /* The size of a new tranche */

  /* Start at the beginning of the list */
  previous = start;
  this = previous->next;

  /* Zip along the list to the first block that fits */
  while ((NULL != this) && (this->size < size)) {
    previous = this;
    this = this->next;
  }

  if (NULL != this) { /* The space is big enough */
    if (this->size > size + MINIMUM_USEFUL_SIZE) { /* This can be split */

      /* BEWARE: casts are necessary to make sure address arithmetic
   is done using byte sizes and not HeaderPtr */

      /* Determine the start address of the new block */
      new_block = (HeaderPtr) ((char*) this + sizeof(size_t) + size);

      /* Set up the new block's header */
      new_block->size = this->size - (sizeof(size_t) + size);
      new_block->next = this->next;

      /* Set up the old block's header */
      this->size = size;
      this->next = new_block;

    }

    /* Remove this block from the list */
    previous->next = this->next;
  } else { /* No block of useful size */

    /* Allocate a new tranche of at least size + sizeof(size_t) */
    new_size = MAX(MINIMUM_TRANCHE_SIZE, size + sizeof(size_t));
    new_block = malloc(new_size);
    new_block->size = new_size - sizeof(size_t);

    /* Prepend it to list of available blocks */
    new_block->next = start->next;
    start->next = new_block;

    /* Recursive call */
    this = (void*) ((char*) zalloc(size) - sizeof(size_t));
  }

  return (HeaderPtr) ((void *) this + sizeof(size_t));
}

/* zfree: free a block of allocated memory. */
void zfree(void* p)
{
  HeaderPtr actual;

  /* Recover a pointer to the initial header */
  actual = p - sizeof(size_t);

  /* Add as the first block in the list */
  actual->next = start->next;
  start->next = actual;
}
