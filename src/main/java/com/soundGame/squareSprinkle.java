
/*
 * Coder: Allison Hart
 * 9/30/2024
 * square-ish shape class - creates strange square shape 
 * 
 * 
 */package com.soundGame;

import processing.core.*;

class squareSprinkle extends Particle {

    squareSprinkle(App main, float x, float y, float vx, float vy, float size, int color) {
        super(main, x, y, vx, vy, size, color);
    }

    @Override
    void display() {
        // Draw the square sprinkle with funky shapes
        main.fill(color);
        main.rect(x, y, size + 25, size + 25); // Main square
        main.ellipse(x, y, 27, 27); // Top-left corner circle/ellipse
        main.ellipse(x + 30, y + 30, size, size); // Top-right corner circle/ellipse
        main.ellipse(x + size, y + size, 50, 50); // Bottom-right corner circle/ellipse
    }

    @Override
    void checkEdges() {
        boolean bounced = false; // Track if a bounce occurred

        // Check for collision with the left or right edges
        if (x <= 0 || x >= main.width) {
            vx *= -1; // Reverse horizontal velocity
            bounced = true;
        }
        // Check for collision with the top or bottom edges
        if (y <= 0 || y >= main.height) {
            vy *= -1; // Reverse vertical velocity
            bounced = true;
        }

        // Play sound effects if a bounce occurred
        if (bounced) {
            playSound(4); // Play sound for squareSprinkle when bouncing off edges
        }
    }

   
}
