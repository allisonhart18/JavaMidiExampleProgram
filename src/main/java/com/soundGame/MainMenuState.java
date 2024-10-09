/*
 * Coder: Allison Hart
 * 9/30/2024
 * MainMenuState class - creates main menu and functionality, press enter and it switches you to the game
 * 
 * 
 */

package com.soundGame;

import processing.core.PApplet;
import processing.core.PConstants;

class MainMenuState extends GameState {
    Controller controller;  // Reference to the Controller

    MainMenuState(PApplet main, Controller controller) {
        super(main);
        this.controller = controller;  // Assign the controller
    }

    void update() {
        main.textSize(32);
        main.fill(0);
        main.text("Main Menu - Press Enter to Play", 150, 300);
    }

    void mouseClicked() {
        
    }

    void keyPressed() {
        if (main.key == PConstants.ENTER) {
            controller.setState(controller.playState);  // Transition to PlayState
        }
    }

  
    void mousePressed() {
       
    }

  
    void display() {
        update(); // Call update to ensure the text gets drawn
    }
    

   
    void mouseDragged() {
        
    }

 
    void mouseReleased() {
        
    }

    @Override
    void reset() {
       
    }
}
