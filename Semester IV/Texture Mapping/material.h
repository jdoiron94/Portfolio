#ifndef MATERIAL_H
#define MATERIAL_H

#include <GL/gl.h>

typedef struct
{
    GLfloat ambient[4];
    GLfloat diffuse[4];
    GLfloat specular[4];
    GLfloat shininess;
} material_t;

extern material_t brass;
extern material_t flat_yellow;
extern material_t red_plastic;
extern material_t white_shiny;

void changeMaterial(material_t* material);

#endif MATERIAL_H