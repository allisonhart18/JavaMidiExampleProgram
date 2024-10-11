/*
 * Coder: Allison Hart
 * 10/10/2024
 * triangle class - creates triangle and has collison logic
 * 
 * 
 * 
 */



 package com.soundGame;

 import processing.core.*;
 
 class triangleSprinkle extends Particle {
     triangleSprinkle(App main, float x, float y, float vx, float vy, float size, int color) {
         super(main, x, y, vx, vy, size, color);
     }
 
     @Override // displays triangle
     void display() {
         main.fill(color);
         main.triangle(x, y, x - size, y + size, x + size, y + size); // Draw triangle
     }
 
     // Get the bounding box of the triangle (approximated with a rectangle)
     float getLeft() {
         return x - size;
     }
 
     float getRight() {
         return x + size;
     }
 
     float getTop() {
         return y;
     }
 
     float getBottom() {
         return y + size;
     }
 
     // Check for collision with another triangleSprinkle object
     boolean checkCollision(triangleSprinkle other) {
         return !(getRight() < other.getLeft() || getLeft() > other.getRight() || 
                  getBottom() < other.getTop() || getTop() > other.getBottom());
     }
 
     // Handle collision by reversing the velocity
     void resolveCollision(triangleSprinkle other, int midiIndex) {
         if (checkCollision(other)) {
             // Reverse both velocities 
             float tempVx = vx;
             float tempVy = vy;
             vx = other.vx;
             vy = other.vy;
             other.vx = tempVx;
             other.vy = tempVy;
             melodyy.start(midiIndex);
         }
     }
 }