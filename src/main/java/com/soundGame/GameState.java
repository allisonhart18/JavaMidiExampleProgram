/*
 * Coder: Allison Hart
 * 9/30/2024
 * GameState class - abstract class called in playState class
 * 
 * 
 */

package com.soundGame;

import processing.core.*;

abstract class GameState {
    App main;

    GameState(App main) {
        this.main = main;
    }

    abstract void update();
    abstract void display();
    abstract void mousePressed();
    abstract void mouseDragged();
    abstract void mouseReleased();
    abstract void keyPressed();

    // Add this method to avoid the override error
    abstract void mouseClicked();

    abstract void reset(); // This method should already be here based on the previous discussion
}


