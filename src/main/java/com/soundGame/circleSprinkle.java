/*
 * Coder: Allison Hart
 * 9/30/2024
 * PcircleSprinkle class - holds all info for circles
 * 
 * 
 */

package com.soundGame;

import processing.core.*;

class circleSprinkle extends Particle {

    circleSprinkle(App main, float x, float y, float vx, float vy, float size, int color) {
        super(main, x, y, vx, vy, size, color);
    }

    @Override
    void display() {
        main.fill(color);  // Use the current color
        main.ellipse(x, y, size+20, size+20);  // Draw the circle with the updated color
    }

    
}
