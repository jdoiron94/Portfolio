#include <math.h>
#include <GL/glut.h>

#define DEGREES 0.0174532925f

void display()
{
  glClearColor(0.0f, 0.5f, 0.5f, 1.0f);
  glClear(GL_COLOR_BUFFER_BIT);
  glColor3f(1.0f, 1.0f, 0.0f);
  glBegin(GL_POLYGON);
    for (float i = 0.0; i < 360.0f * DEGREES; i += DEGREES) {
      glVertex2f(sinf(i), cosf(i));
    }
  glEnd();
  glColor3f(1.0f, 1.0f, 1.0f);
  glBegin(GL_POLYGON);
    for(float i = 0.0f; i < 360.0f * DEGREES; i+= DEGREES) {
      glVertex2f(sinf(i) / 3.0f + 0.45f, cosf(i) / 3.0f + 0.45f);
    }
  glEnd();
  glBegin(GL_POLYGON);
    for (float i = 0.0f; i < 360.0f * DEGREES; i += DEGREES) {
      glVertex2f(sinf(i) / 3.0f - 0.45f, cosf(i) / 3.0f + 0.45f);
    }
  glEnd();
  glColor3f(1.0f, 0.0f, 1.0f);
  glBegin(GL_POLYGON);
    for (int i = 90; i < 270; i++) {
      glVertex2f(sinf(i * DEGREES) / 1.25f, cosf(i * DEGREES) / 1.25f);
    }
  glEnd();
  glFlush();
}

int main(int argc, char** argv)
{
  glutInit(&argc, argv);
  glutCreateWindow("2D Smiley");
  glutDisplayFunc(display);
  glutMainLoop();
}
