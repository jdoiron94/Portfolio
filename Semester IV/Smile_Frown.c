#include <math.h>
#include <GL/glut.h>

#define DEGREES 0.0174532925f

int _state = 0;
int previous_state = 0;

float theta = 0.0f;

void draw_features()
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
}

void draw_mouth()
{
  glColor3f(1.0f, 0.0f, 1.0f);
  glBegin(GL_POLYGON);
    switch (_state) {
      case 0:
        for (int i = 90; i < 270; i++) {
          glVertex2f(sinf(i * DEGREES) / 1.25f, cosf(i * DEGREES) / 1.25f);
        }
        break;
        case 1:
        for (int i = 270; i < 450; i++) {
          glVertex2f(sinf(i * DEGREES) / 1.25f, (cosf(i * DEGREES) / 1.25f) - 0.6f);
        }
        break;
    }
  glEnd();
}

void display()
{
  glClearColor(1.0f, 1.0f, 0.0f, 1.0f);
  glClear(GL_COLOR_BUFFER_BIT);
  glColor3f(1.0f, 0.0f, 0.0f);
  glBegin(GL_POLYGON);
  glVertex2f(sinf(theta), cosf(theta));
  glVertex2f(-sinf(theta), cosf(theta));
  glVertex2f(sinf(theta), -cosf(theta));
  glVertex2f(-sinf(theta), -cosf(theta));
  glEnd();
  glFlush();
}

void idle()
{
  if (theta <= 0.85f) {
    theta += 0.15f;
  } else {
    theta = 0.0f;
  }
  glutPostRedisplay();
}

void process_key(unsigned char c, int x, int y)
{
  switch (c) {
    case 'f':
      previous_state = _state;
      _state = 1;
      break;
    case 's':
      previous_state = _state;
      _state = 0;
      break;
  }
  if (_state != previous_state) {
    glutPostRedisplay();
  }
}

void process_mouse(int button, int state, int x, int y)
{
  if (state == GLUT_DOWN) {
    previous_state = _state;
    _state = _state == 0 ? 1 : 0;
    if (_state != previous_state) {
      glutPostRedisplay();
    }
  }
}

int main(int argc, char** argv)
{
  glutInit(&argc, argv);
  glutCreateWindow("Smile/Frown");
  glutDisplayFunc(display);
  glutIdleFunc(idle);
  glutMainLoop();
}
