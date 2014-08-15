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
    //glTexCoord2f(theta, phi);
    glVertex3f(sin(theta)*cos(phi), cos(theta)*cos(phi), sin(phi));
}

typedef struct
{
    GLuint columns;
    GLuint rows;
    GLubyte* pixels;
} image_t;

image_t image;

image_t read_ppm(char* name)
{
    FILE* file; /* Image file */
    char buffer[1000]; /* Buffer for scanf to read a line */
    GLubyte* pixel_cursor; /* The current byte position in the image */
    char first; /* The first character in a line to test for comments */
    image_t image; /* The image to be returned */
    int scale; /* The scaling factor in the image */
    int red; /* A temporary variable to satisfy scanf */
    int green; /* A temporary variable to satisfy scanf */
    int blue; /* A temporary variable to satisfy scanf */

    /* Open file for reading */
    file = fopen(name, "r");
    if (NULL == file )
	{
        fprintf(stderr, "No such file [%s].\n", name);
        exit(1);
    }

    /* Check that the file is a PPM file */
    fscanf(file, "%1000[^\n]", buffer);

    if (('P' != buffer[0]) || ('3' != buffer[1]))
    {
        fprintf(stderr, "%s is not a PPM file.\n", name);
        exit(1);
    }
    getc(file); /* Skip end of line */

    /* Strip off comment lines */
    do
    {
        first = getc(file);
        if ('#' == first)
        {
            fscanf(file, "%1000[^\n]", buffer);
            getc(file); /* Skip end of line */
        } else
        {
            ungetc(first, file);
        }
    } while ('#' == first);

    /* Read in the sizes */
    fscanf(file, "%d %d %d", &image.rows, &image.columns, &scale);

    if (255 != scale)
    {
        fprintf(stderr, "Cannot read PPM file with scaling factor.\n");
        exit(2);
    }

    /* Allocate space for the image */
    image.pixels = malloc(3 * image.rows * image.columns * sizeof(GLubyte));

    /* Read in the pixels */
    pixel_cursor = image.pixels;
    for (int row = 0; row < image.rows; row++)
    {
        for (int column = 0; column < image.columns; column++)
        {
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
    for (float phi = start + cap_size; phi < finish - cap_size; phi+=degree_step)
    {
        float phi_radians = phi * DEGREES_TO_RADIANS;
        float phi_next_radians = (phi + degree_step) * DEGREES_TO_RADIANS;
        glBegin(GL_TRIANGLE_STRIP); {
            for (float theta = -180.0; theta < 180.0; theta+=degree_step) {
                float theta_radians = theta * DEGREES_TO_RADIANS;
                if (texture == 1)
                {
                    glTexCoord2f(theta / 180.0, (phi / 90.0));
                }
                point(theta_radians, phi_radians);
                //glTexCoord2f(phi / (2 * 3.14159), theta / 3.14159);
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
            //glTranslatef(1.0, 1.0, 0.0);
            //glScalef(2.0, 2.0, 0.0);
            // translate x and y by 1, divide resulting coordinates by 2
            //glTranslate2f(theta, 1.0);
            glBegin(GL_TRIANGLE_FAN); {
                point(180.0 * DEGREES_TO_RADIANS, 90.0 * DEGREES_TO_RADIANS);
                for (float theta = -180.0; theta < 180.0; theta+=degree_step)
                {
                    float theta_radians = theta * DEGREES_TO_RADIANS;
                    point(theta_radians, (90.0 - cap_size) * DEGREES_TO_RADIANS);
                }
                point(180.0 * DEGREES_TO_RADIANS, (90.0 - cap_size) * DEGREES_TO_RADIANS);
            } glEnd();
            glBegin(GL_TRIANGLE_FAN); {
                point(-180.0 * DEGREES_TO_RADIANS, -90.0 * DEGREES_TO_RADIANS);
                for (float theta = -180.0; theta < 0.0; theta+=degree_step)
                {
                    float theta_radians = 180.0 - theta * DEGREES_TO_RADIANS;
                    point(theta_radians, (-90.0 + cap_size) * DEGREES_TO_RADIANS);
                }
                point((180.0 - 180.0) * DEGREES_TO_RADIANS, (-90.0 + cap_size) * DEGREES_TO_RADIANS);
            } glEnd();
        } glPopAttrib();
    } glEndList();
}