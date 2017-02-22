//
//  main.cpp
//  CompGraphics-Poj4
//
//  Created by Mingjing Tang on 1/25/17.
//  Copyright © 2017 Mingjing Tang. All rights reserved.
//

//#include <iostream>
//
//int main(int argc, const char * argv[]) {
//    // insert code here...
//    std::cout << "Hello, World!\n";
//    return 0;
//}
//





//
//  main.cpp
//  project4
//
//  Created by Mingjing Tang on 12/4/14.
//  Copyright (c) 2014 Mingjing Tang. All rights reserved.
//

//#include <stdlib.h>//windows
//#include <GL/glut.h>//windows
#include <iostream>
#include <GLUT/GLUT.h>
#include <math.h>
using namespace std;

GLfloat pos[] = {1.5, -2, 1, 0};              //light position

GLfloat amb[] = {0,0,1,0};

GLfloat spe[] = {0.25, 0.25, 0.25, 1.0};            //Property for front and back

GLfloat theta = 0, dt = 0, highest = 0, dropYpos = 0,         //speed of the rotate

axes[3][3] = {
    {1,0,0},
    {0,1,0},
    {0,0,1}
};
int axis = 0;

GLfloat back_amb_diff[] = {0.52, 0.39, 0.39, 1.0};    //outside (dustyrose) property R G B alpha
GLfloat front_amb_diff[] = {0.35, 0.16, 0.14, 1.0};     //inside (verydarkbrown) property  R G B alpha

GLfloat back_amb_dif[] = {0.137255, 0.556863, 0.419608, 1.0};//outside property R G B alpha
GLfloat front_amb_dif[] = {0.0, 0.0, 1.0, 1.0};     //inside property  R G B alpha

GLfloat front_amb_di[] = {0.8, 0.498039, 0.196078, 1.0};
GLfloat back_amb_di[] = {1.0, 0.598039, 0.196078, 1.0};

void display(void){
    if (theta >= 45 || theta <= 0) dt = 0;
    
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    
    /**
     * TEAPOT
     */
    glPushMatrix();
    
    glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, front_amb_diff);
    glMaterialfv(GL_BACK, GL_AMBIENT_AND_DIFFUSE, back_amb_diff);
    glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, spe);
    glLightfv(GL_LIGHT0, GL_AMBIENT, amb);
    
    if (theta <= 45 && theta >= 0) {
        glRotated(theta, 0, 0, 1);//angle, x, y, z
    }
    else if(theta > 45){
        glRotated(highest, 0, 0, 1);//angle, x, y, z
    }
    glTranslated(2.2, -0.5, 3);
    glRotated(180, 0, 1, 0);
    glutSolidTeapot(1);        //Size of the teapot
    
    glPopMatrix();
    
    /**
     *WATER DROP
     */
    glPushMatrix();
    
    glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, front_amb_dif);
    glMaterialfv(GL_BACK, GL_AMBIENT_AND_DIFFUSE, back_amb_dif);
    glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, spe);
    glLightfv(GL_LIGHT0, GL_AMBIENT, amb);
    
    if (theta >= 45) {
        glTranslated(0, dropYpos, 4);
        glTranslated(0, 0, 2);
        glutSolidSphere(0.3, 20, 20);
        glPopMatrix();
    }
    
    /**
     * CUP
     */
    glPushMatrix();
    
    glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, front_amb_di);
    glMaterialfv(GL_BACK, GL_AMBIENT_AND_DIFFUSE, back_amb_di);
    glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, spe);
    glLightfv(GL_LIGHT0, GL_AMBIENT, amb);
    glTranslated(0, -2, 3);
    glRotated(95, 1, 0, 0);
    
    GLUquadricObj *quadratic;
    quadratic = gluNewQuadric();
    gluCylinder(quadratic, 0.4, 0.2, 1.0, 20, 20);
    
    glPopMatrix();
    
    glPushMatrix();
    glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, front_amb_di);
    glMaterialfv(GL_BACK, GL_AMBIENT_AND_DIFFUSE, back_amb_di);
    glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, spe);
    glLightfv(GL_LIGHT0, GL_AMBIENT, amb);
    
    glTranslated(0, -3, 3);
    glRotated(120, 1, 0, 0);
    GLUquadricObj *quadratic2;
    quadratic2 = gluNewQuadric();
    
    gluDisk(quadratic2, 0.01, 1.0, 200, 2);
    glPopMatrix();
    glutSwapBuffers();
}


void idle(void) {
    theta = (theta < 360)? theta + dt: dt;
    if (theta <= 45) {
        highest = theta;
    }
    if (theta >= 45) {
        if (dropYpos <= -3.3) {
            dropYpos = 0;
        }
        dropYpos = dropYpos - 0.02;
    }
    cout << theta << endl;
    glutPostRedisplay();
}
void keyboardf(int key, int x, int y) {
    switch(key) {
        case GLUT_KEY_UP:
            if (theta >= 45 ) {
                dt = 0;
            }else
                dt = dt + 0.1;
            if (dt > 10) dt = 10;

            break;
        case GLUT_KEY_DOWN:
            if (theta <= 0) {
                dt = 0;
                }else
                dt = dt - 0.1;
            break;
    }
    glutPostRedisplay();
    
}

void operationIntro() {
    cout << "Project 4 - Teapot with Water Drop." << endl;
    cout << "Mingjing Tang" << endl;
    cout << endl;
    cout << "'UP'\t teapot up." << endl;
    cout << "'DOWN'\t teapot down." << endl;
}
int main(int argc, char ** argv) {
    glutInit(& argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
    glutInitWindowSize(800, 600);
    glutInitWindowPosition(200, 100);
    glutCreateWindow("project 4");
    glClearColor(0.5, 0.5, 0.5, 0.0);
    
    glEnable(GL_DEPTH_TEST);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(45, 1, 2, 10);
    
    glMaterialf(GL_FRONT_AND_BACK,  GL_SHININESS,           50);
    glLightModeli(GL_LIGHT_MODEL_TWO_SIDE, GL_TRUE);
    glMatrixMode(GL_MODELVIEW);
    
    glLightfv(GL_LIGHT0, GL_POSITION, pos);
    
    glEnable(GL_LIGHTING);
    glEnable(GL_LIGHT0);
    glShadeModel(GL_SMOOTH);
    
    gluOrtho2D(-4.0, 4.0, -3.0, 3.0);
    glutDisplayFunc(display);
    glutSpecialFunc(keyboardf);
    
    glutIdleFunc(idle);
    operationIntro();
    glutMainLoop();
}


