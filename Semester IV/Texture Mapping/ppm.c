#include "ppm.h"

#include <stdlib.h>
#include <stdio.h>

#include <GL/gl.h>

image_t readPPM(char* name)
{
  FILE* file;
  char buffer[1000];
  GLubyte* pixel_cursor;
  char first;
  image_t image;
  int scale;
  int red;
  int green;
  int blue;
  file = fopen(name, "r");
  if (NULL == file ) {
    fprintf(stderr, "No such file [%s].\n", name);
    exit(1);
  }
  fscanf(file, "%1000[^\n]", buffer);
  if (('P' != buffer[0]) || ('3' != buffer[1])) {
    fprintf(stderr, "%s is not a PPM file.\n", name);
    exit(1);
   }
  getc(file);
  do {
    first = getc(file);
    if ('#' == first) {
      fscanf(file, "%1000[^\n]", buffer);
      getc(file);
    } else {
      ungetc(first, file);
    }
  } while ('#' == first);
  fscanf(file, "%d %d %d", &image.rows, &image.columns, &scale);
  if (255 != scale) {
    fprintf(stderr, "Cannot read PPM file with scaling factor.\n");
    exit(2);
  }
  image.pixels = malloc(3 * image.rows * image.columns * sizeof(GLubyte));
  pixel_cursor = image.pixels;
  for (int row = 0; row < image.rows; row++) {
    for (int column = 0; column < image.columns; column++) {
      fscanf(file, "%d %d %d", &red, &green, &blue);
      *pixel_cursor = (GLubyte) red;
      pixel_cursor = pixel_cursor + 1;
      *pixel_cursor = (GLubyte) green;
      pixel_cursor = pixel_cursor + 1;
      *pixel_cursor = (GLubyte) blue;
      pixel_cursor = pixel_cursor + 1;
    }
  }
  return image;
}
