#ifndef PPM_H
#define PPM_H

#include <GL/gl.h>

typedef struct
{
  GLuint rows;
  GLuint columns;
  GLubyte* pixels;
} image_t;

image_t readPPM(char* name);

#endif PPM_H
