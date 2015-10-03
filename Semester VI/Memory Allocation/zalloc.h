#ifndef _ZALLOC_H_
#define _ZALLOC_H_

#include <stdlib.h>

void zinit(void);
void* zalloc(size_t size);
void zfree(void* p);

#endif _ZALLOC_H_
