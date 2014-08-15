#include "ppm.h"

#include <stdlib.h>
#include <stdio.h>

#include <GL/gl.h>

image_t readPPM(char* name)
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
    // + 3 * image.rows * image.columns * sizeof(GLubyte);
    // pixel_cursor = pixel_cursor - 3 * image.columns * sizeof(GLubyte);
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
    //    pixel_cursor = pixel_cursor - 2 * 3 * image.columns * sizeof(GLubyte);
    }
    return image;
}