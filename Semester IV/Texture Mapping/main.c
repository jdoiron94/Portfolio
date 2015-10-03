#include <stdio.h>
#include <math.h>

#include <GL/glut.h>

#include "math_constants.h"
#include "material.h"
#include "sphere.h"

static GLint theta[3] = {0, 0, 0};

static GLuint last_time;

void init()
{
  initSphere();
  glEnable(GL_DEPTH_TEST);
  glEnable(GL_BLEND);
  glShadeModel(GL_SMOOTH);
  glEnable(GL_NORMALIZE);
  glEnable(GL_LIGHTING);
  glEnable(GL_LIGHT0);
  glLightModeli(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_TRUE);
}

void reshape(int width, int height)
{
  int size = height > width ? width : height;
  if (height > width) {
    glViewport(0, (height - size) / 2, size, size);
  } else {
    glViewport((width - size) / 2, 0, size, size);
  }
}

void idle()
{
  int direction;
  GLuint time;
  if (0 == last_time) {
    last_time = time;
  }
  time = glutGet(GLUT_ELAPSED_TIME);
  if (10 < (time - last_time)) {
    direction = rand() % 3;
    theta[direction] = (theta[direction] + 3) % 360;
    last_time = time;
    glutPostRedisplay();
  }
}

void display()
{
  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  gluPerspective(80.0, 1.0, 0.25, 8.0);
  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity();
  gluLookAt(1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
  glRotatef(theta[0], 1.0, 0.0, 0.0);
  glRotatef(theta[1], 0.0, 1.0, 0.0);
  glRotatef(theta[2], 0.0, 0.0, 1.0);
  changeMaterial(&flat_yellow);
  glScalef(1.0, 1.0, 0.33);
  glCallList(sphere_list);
  glutSwapBuffers();
}

int main(int argc, char** argv)
{
  glutInit(&argc, argv);
  glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
  glutCreateWindow("Spinning M&M");
  init();
  glutReshapeFunc(reshape);
  glutIdleFunc(idle);
  glutDisplayFunc(display);
  glutMainLoop();
}
