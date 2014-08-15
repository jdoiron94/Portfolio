#include "material.h"

material_t brass =
{
    {0.33, 0.22, 0.03, 1.0},
    {0.78, 0.57, 0.11, 1.0},
    {0.99, 0.91, 0.81, 1.0},
    27.8
};

material_t flat_yellow =
{
    {0.25, 0.25, 0.0, 1.0},
    {0.5, 0.5, 0.0, 1.0},
    {1.0, 1.0, 0.0, 1.0},
    200.0
};
 
material_t red_plastic =
{
    {0.3, 0.0, 0.0, 0.4},
    {0.6, 0.0, 0.0, 0.4},
    {0.8, 0.6, 0.6, 0.4},
    32.0
};

material_t white_shiny =
{
    {1.0, 1.0, 1.0, 1.0},
    {1.0, 1.0, 1.0, 1.0},
    {1.0, 1.0, 1.0, 1.0},
    100.0
};

void changeMaterial(material_t* material)
{
    glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, material->ambient);
    glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, material->diffuse);
    glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, material->specular);
    glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, material->shininess);
}