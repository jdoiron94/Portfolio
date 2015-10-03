#include "sphere.h"
#include "math_constants.h"

#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <GL/glut.h>

#define degree_step 1.0
#define cap_size 1.0

GLuint sphere_list;

void point(float theta, float phi)
{
  glNormal3f(sin(theta)*cos(phi), cos(theta)*cos(phi), sin(phi));
  glVertex3f(sin(theta)*cos(phi), cos(theta)*cos(phi), sin(phi));
}

typedef struct
{
  GLuint columns;
  GLuint rows;
  GLubyte* pixels;
} image_t;

image_t image;

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

void draw_hemisphere(float start, float finish, int texture)
{
  for (float phi = start + cap_size; phi < finish - cap_size; phi+=degree_step) {
    float phi_radians = phi * DEGREES_TO_RADIANS;
    float phi_next_radians = (phi + degree_step) * DEGREES_TO_RADIANS;
    glBegin(GL_TRIANGLE_STRIP); {
      for (float theta = -180.0; theta < 180.0; theta+=degree_step) {
        float theta_radians = theta * DEGREES_TO_RADIANS;
        if (texture == 1) {
          glTexCoord2f(theta / 180.0, (phi / 90.0));
        }
        point(theta_radians, phi_radians);
        point(theta_radians, phi_next_radians);
      }
      point(-180.0 * DEGREES_TO_RADIANS, phi_radians);
      point(-180.0 * DEGREES_TO_RADIANS, phi_next_radians);
    } glEnd();
  }
}

void initSphere() {
  image = read_ppm("screenshot.ppm");
  sphere_list = glGenLists(1);
  glNewList(sphere_list, GL_COMPILE); {
    glPushAttrib(GL_ALL_ATTRIB_BITS); {
      glEnable(GL_TEXTURE_2D);
      glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, image.rows, image.columns, 0, GL_RGB, GL_UNSIGNED_BYTE, image.pixels);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
      glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
      draw_hemisphere(-90.0, 0.0, 1);
      glDisable(GL_TEXTURE_2D);
      draw_hemisphere(0.0, 90.0, 0);
      glBegin(GL_TRIANGLE_FAN); {
       point(180.0 * DEGREES_TO_RADIANS, 90.0 * DEGREES_TO_RADIANS);
        for (float theta = -180.0; theta < 180.0; theta+=degree_step) {
          float theta_radians = theta * DEGREES_TO_RADIANS;
          point(theta_radians, (90.0 - cap_size) * DEGREES_TO_RADIANS);
        }
        point(180.0 * DEGREES_TO_RADIANS, (90.0 - cap_size) * DEGREES_TO_RADIANS);
      } glEnd();
      glBegin(GL_TRIANGLE_FAN); {
        point(-180.0 * DEGREES_TO_RADIANS, -90.0 * DEGREES_TO_RADIANS);
        for (float theta = -180.0; theta < 0.0; theta+=degree_step) {
          float theta_radians = 180.0 - theta * DEGREES_TO_RADIANS;
          point(theta_radians, (-90.0 + cap_size) * DEGREES_TO_RADIANS);
        }
        point((180.0 - 180.0) * DEGREES_TO_RADIANS, (-90.0 + cap_size) * DEGREES_TO_RADIANS);
      } glEnd();
    } glPopAttrib();
  } glEndList();
}
