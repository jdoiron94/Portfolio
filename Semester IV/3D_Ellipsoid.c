#include <stdlib.h>
#include <math.h>
#include <GL/glut.h>

#ifndef M_PI
  #define M_PI 3.14159
#endif

#define SPHERE 1

int last_time;
GLint theta[3] = {0, 0, 0};

const GLfloat position[] = {0.0, 2.0, -1.5, 1.0};
const GLfloat diffuse[] = {0.6, 0.5, 0.6, 1.0};
const GLfloat ambient[] = {0.4, 0.4, 0.5, 1.0};

void draw_mnm(int latitudes, int longitudes)
{
  for (int i = 0; i <= latitudes; i++) {
    double lat_0[] = {M_PI * (-0.5 + (double) (i - 1) / latitudes), sin(lat_0[0]) / 2.75, cos(lat_0[0])};
    double lat_1[] = {M_PI * (-0.5 + (double) i / latitudes), sin(lat_1[0]) / 2.75, cos(lat_1[0])};
    glBegin(GL_QUAD_STRIP);
      for (int j = 0; j <= longitudes; j++) {
        double longitude = 2 * M_PI * (double) (j - 1) / longitudes;
        double coordinate[] = {cos(longitude), sin(longitude)};
        glNormal3f(coordinate[0] * lat_0[2], coordinate[1] * lat_0[2], lat_0[1]);
        glVertex3f(coordinate[0] * lat_0[2], coordinate[1] * lat_0[2], lat_0[1]);
        glNormal3f(coordinate[0] * lat_1[2], coordinate[1] * lat_1[2], lat_1[1]);
        glVertex3f(coordinate[0] * lat_1[2], coordinate[1] * lat_1[2], lat_1[1]);
      }
    glEnd();
  }
}

void init()
{
  glNewList(SPHERE, GL_COMPILE);
    glPushAttrib(GL_ALL_ATTRIB_BITS);
    glColor3f(1.0, 0.0, 0.0);
    draw_mnm(25, 25);
    glPopAttrib();
  glEndList();
  glClearColor(0.0, 0.0, 0.0, 1.0);
  glEnable(GL_DEPTH_TEST);
  glEnable(GL_LIGHTING);
  glLightModelfv(GL_LIGHT_MODEL_AMBIENT, ambient);
  glEnable(GL_LIGHT0);
  glLightfv(GL_LIGHT0, GL_POSITION, position);
  glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse);
  glEnable(GL_COLOR_MATERIAL);
  glColorMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE);
}

void display()
{
  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  glCallList(SPHERE);
  glRotatef(theta[0], 1.0, 0.0, 0.0);
  glRotatef(theta[1], 0.0, 1.0, 0.0);
  glRotatef(theta[2], 0.0, 0.0, 1.0);
  glutSwapBuffers();
}

void idle()
{
  int time = glutGet(GLUT_ELAPSED_TIME);
  if (time - last_time >= 200) {
    int direction = rand() % 3;
    theta[direction] = (theta[direction] + 3) % 360;
    last_time = glutGet(GLUT_ELAPSED_TIME);
    glutPostRedisplay();
  }
}

int main(int argc, char** argv)
{
  glutInit(&argc, argv);
  glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
  glutCreateWindow("3D M&M");
  init();
  glutIdleFunc(idle);
  glutDisplayFunc(display);
  glutMainLoop();
}
