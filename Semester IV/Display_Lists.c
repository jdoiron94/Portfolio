#include <math.h>
#include <GL/glut.h>

#define DEGREES 0.0174532925f

#define HEAD 1
#define EYES 2
#define SMILE 3
#define FROWN 4
#define SMILEY_FACE 5
#define FROWNY_FACE 6

int _state = 0;
int previous_state = 0;

float theta = 0.0f;

void draw_head()
{
    glNewList(HEAD, GL_COMPILE);
        glPushAttrib(GL_CURRENT_BIT);
        glColor3f(1.0f, 1.0f, 0.0f);
        glBegin(GL_POLYGON);
            for (float i = 0.0; i < 360.0f * DEGREES; i += DEGREES)
            {
                glVertex2f(sinf(i), cosf(i));
            }
        glEnd();
    glEndList();
}

void draw_eyes()
{
    glNewList(EYES, GL_COMPILE);
        glPushAttrib(GL_CURRENT_BIT);
        glColor3f(1.0f, 1.0f, 1.0f);
        glBegin(GL_POLYGON);
            for(float i = 0.0f; i < 360.0f * DEGREES; i+= DEGREES)
            {
                glVertex2f(sinf(i) / 3.0f + 0.45f, cosf(i) / 3.0f + 0.45f);
            }
        glEnd();
        glBegin(GL_POLYGON);
            for (float i = 0.0f; i < 360.0f * DEGREES; i += DEGREES)
            {
                glVertex2f(sinf(i) / 3.0f - 0.45f, cosf(i) / 3.0f + 0.45f);
            }
        glEnd();
    glEndList();
}

void draw_smile()
{
    glNewList(SMILE, GL_COMPILE);
        glPushAttrib(GL_CURRENT_BIT);
        glColor3f(1.0f, 0.0f, 1.0f);
        glBegin(GL_POLYGON);
            for (int i = 90; i < 270; i++)
            {
                glVertex2f(sinf(i * DEGREES) / 1.25f, cosf(i * DEGREES) / 1.25f);
            }
        glEnd();
    glEndList();
}

void draw_frown()
{
    glNewList(FROWN, GL_COMPILE);
        glPushAttrib(GL_CURRENT_BIT);
        glColor3f(1.0f, 0.0f, 1.0f);
        glBegin(GL_POLYGON);
            for (int i = 270; i < 450; i++)
            {
                glVertex2f(sinf(i * DEGREES) / 1.25f, (cosf(i * DEGREES) / 1.25f) - 0.6f);
            }
        glEnd();
    glEndList();
}

void init()
{
    draw_head();
    draw_eyes();
    draw_smile();
    draw_frown();
    glNewList(SMILEY_FACE, GL_COMPILE);
        glCallList(HEAD);
        glCallList(EYES);
        glCallList(SMILE);
        glEndList();
    glNewList(FROWNY_FACE, GL_COMPILE);
        glCallList(HEAD);
        glCallList(EYES);
        glCallList(FROWN);
    glEndList();
}

void display()
{
    glClearColor(0.0f, 0.5f, 0.5f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT);
    glCallList(_state == 0 ? SMILEY_FACE : FROWNY_FACE);
    glFlush();
}

void process_key(unsigned char c, int x, int y)
{
    switch (c)
    {
        case 'f':
            previous_state = _state;
            _state = 1;
            break;
        case 's':
            previous_state = _state;
            _state = 0;
            break;
    }
    if (_state != previous_state)
    {
        glutPostRedisplay();
    }
}

void process_mouse(int button, int state, int x, int y)
{
    if (state == GLUT_DOWN)
    {
        previous_state = _state;
        _state = _state == 0 ? 1 : 0;
        if (_state != previous_state)
        {
            glutPostRedisplay();
        }
    }
}

int main(int argc, char** argv)
{
    glutInit(&argc, argv);
    glutCreateWindow("Display Lists");
    init();
    glutDisplayFunc(display);
    glutKeyboardFunc(process_key);
    glutMouseFunc(process_mouse);
    glutMainLoop();
}
