/*
 * Coder: Allison Hart
 * 9/30/2024
 * Controller class - main manager for handeling different game states
 * and user interactions
 * 
 */


 package com.soundGame;

 import processing.core.PApplet;
 
 public class Controller {
     PApplet main;
 
     // Current game state and other states
     GameState currentState;
     GameState mainMenuState;
     GameState gameOverState;
     GameState playState;
 
     // Track if the game is over
     boolean isGameOver = false;
 
     public Controller(PApplet main) {
         this.main = main;
         playState = new PlayState(main, this);
         mainMenuState = new MainMenuState(main, this);
         gameOverState = new GameOverState(main, this);
 
         // Start with the main menu state
         currentState = mainMenuState;
     }
 
     public void update() {
         // Update the current state
         if (!isGameOver) {
             currentState.update();
         }
     }
 
     public void display() {
         // Display the current state
         if (!isGameOver) {
             currentState.display();
         }
     }
 
     public void setState(GameState state) {
         currentState = state;
 
         // Set the game over flag if the new state is GameOverState
         isGameOver = state instanceof GameOverState;
     }
 
     // Delegate input events to the current state
     public void mousePressed() {
         currentState.mousePressed();
     }
 
     public void mouseDragged() {
         currentState.mouseDragged();
     }
 
     public void mouseReleased() {
         currentState.mouseReleased();
     }
 
     public void keyPressed() {
         currentState.keyPressed();
     }
 }
 