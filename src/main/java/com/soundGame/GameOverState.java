/*
 * Coder: Allison Hart
 * 9/30/2024
 * GameOver class - screen that appears after player has reached 100 points
 * 
 * 
 */

package com.soundGame;
import processing.core.PApplet;
import processing.core.PConstants;

class GameOverState extends GameState {
    Controller controller;
    float buttonX, buttonY, buttonWidth, buttonHeight;

    GameOverState(PApplet main, Controller controller) {
        super(main);
        this.controller = controller;

        buttonX = main.width / 2 - 100;
        buttonY = main.height / 2 + 50;
        buttonWidth = 200;
        buttonHeight = 50;
    }

    @Override
    void update() {
        main.textSize(32);
        main.fill(0);
        main.text("Game Over", 250, 200);
        main.text("Press Enter to Restart", 200, 250);

        // Draw the restart button
        main.fill(0, 100, 200);
        main.rect(buttonX, buttonY, buttonWidth, buttonHeight);

        main.fill(255);
        main.textSize(20);
        main.textAlign(PConstants.CENTER, PConstants.CENTER);
        main.text("Restart", buttonX + buttonWidth / 2, buttonY + buttonHeight / 2);
    }
    @Override
void reset() {
    // GameOverState may not need to reset anything
}


    @Override
    void keyPressed() {
        if (main.key == PConstants.ENTER) {
            controller.playState = new PlayState(main, controller);  // Reset PlayState
            controller.setState(controller.mainMenuState);  // Transition to MainMenuState
        }
    }

    @Override
    void mousePressed() {
        // Check if the restart button is clicked
        if (main.mouseX > buttonX && main.mouseX < buttonX + buttonWidth &&
            main.mouseY > buttonY && main.mouseY < buttonY + buttonHeight) {
            controller.playState = new PlayState(main, controller);  // Reset PlayState
            controller.setState(controller.mainMenuState);  // Transition to MainMenuState
        }
    }

    @Override
    void display() {
        // Nothing extra here, handled in update()
    }
/* 
     @Override
    void mouseClicked() { }
    */

    
    @Override
    void mouseDragged() {  }
    
    

    @Override
    void mouseReleased() { /* Not used */ }

    @Override
    void mouseClicked() {
       
    }
}

