
/*
 * Coder: Allison Hart
 * 9/30/2024
 * square-ish shape class - creates strange square shape 
 * 
 * 
 */
package com.soundGame;

import processing.core.PApplet;

class squareSprinkle extends Particle {
    squareSprinkle(PApplet main, float x, float y, float vx, float vy, float size, int color) {
        super(main, x, y, vx, vy, size, color);
    }

    @Override
    void display() {
        //makes funky square circle 
        main.fill(color);
        main.rect(x, y, size+25, size+25);
        main.ellipse(x, y, 27, 27); // Top-left corner circle/ellipse
            main.ellipse(x + 30, y +30, size, size); // Top-right corner circle/ellipse
            main.ellipse(x + size, y + size, 50, 50);
        }
   
    
}
