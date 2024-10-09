/*
 * Coder: Allison Hart
 * 9/30/2024
 * Particle Parent class - holds all basic info for shapes
 * 
 * 
 */

package com.soundGame;

import processing.core.PApplet;

abstract class Particle {
    float x, y;
    float vx, vy; // Velocity
    float size;
    int color;
    PApplet main;
    boolean isDragging = false;  // Track dragging state
    float offsetX, offsetY;  // Offset to track mouse position relative to the particle

    Particle(PApplet main, float x, float y, float vx, float vy, float size, int color) {
        this.main = main;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.size = size;
        this.color = color;
    }

   void move() {
        // Only move if not being dragged
        if (!isDragging) {
            x += vx;
            y += vy;
            checkEdges();
        }
    }

    void checkEdges() {
        if (x <= 0 || x >= main.width) vx *= -1; // Bounce horizontally
        if (y <= 0 || y >= main.height) vy *= -1; // Bounce vertically
    }

    // Check if the particle is being clicked
    boolean isMouseOver() {
        return PApplet.dist(main.mouseX, main.mouseY, x, y) < size / 2;
    }

    // Start dragging the particle if the mouse is over it
    void mousePressed() {
        if (isMouseOver()) {
            isDragging = true;
            offsetX = main.mouseX - x;
            offsetY = main.mouseY - y;

            main.noStroke();
            color = main.color(main.random(255), main.random(255), main.random(255));
        }
    }

    // Update position to follow the mouse while dragging
    void mouseDragged() {
        if (isDragging) {
            x = main.mouseX - offsetX;
            y = main.mouseY - offsetY;
        }
    }

    // Stop dragging when the mouse is released
    void mouseReleased() {
        isDragging = false;
    }

    void display() {}
    
    

    void keyPressed(){};
  
    void mouseClicked(){};
    
}
