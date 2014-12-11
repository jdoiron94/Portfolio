#include <math.h>
#include <GL/glut.h>

#ifndef M_PI
	#define M_PI 3.14159
#endif

#define segments 360

GLuint circle_list;

int last_time;
GLint theta[3] = {0, 0, 0};

void initCircle()
{
  float radians;
  circle_list = glGenLists(1);
  glNewList(circle_list, GL_COMPILE); {
    glBegin(GL_TRIANGLE_STRIP); {
      glNormal3f(0.0f, 0.0f, 1.0f);
      glVertex3f(0.0f, 0.0f, 0.0f);
      for (int index = 0; index < segments; index++) {
        radians = 2.0f * M_PI * (((float) (segments - index)) / ((float) segments));
        glVertex3f(sin(radians), cos(radians), 0.0f);
        glNormal3f(sin(radians), cos(radians), 0.0f);
        glVertex3f(0.0f, 0.0f, 0.0f);
        glNormal3f(0.0f, 0.0f, 0.0f);
      }
      glVertex3f(sin(0.0f), cos(0.0f), 0.0f);
      glNormal3f(sin(0.0f), cos(0.0f), 0.0f);
    }
   glEnd(); 
  } glEndList();
  glEnable(GL_DEPTH_TEST);
}

void display()
{
  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  glCallList(circle_list);
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
  glutCreateWindow("Circle");
  initCircle();
  //glutIdleFunc(idle);
  glutDisplayFunc(display);
  glutIdleFunc(idle);
  glutMainLoop();
}