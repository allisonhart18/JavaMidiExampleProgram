/*
 * c2017-2024 Courtney Brown 
 * Class: Main Class for Hello World for CC3 Class Projects streaming MIDI, etc.
 * Description: Demonstration of MIDI file manipulations, etc. & 'MelodyPlayer' sequencer, 2024 - add processing/interactivity
 * 
 */
package com.soundGame;

import processing.core.PApplet;

public class App extends PApplet {
    static String filePath = "mid" + System.getProperty("file.separator"); // Path to the midi file folder

    // Names of midi files used in the game
    String[] midiFiles = {"bounce", "rightPlace", "grab"};

    // Melody manager for handling midi
    melodyManager melodyManager = new melodyManager();

    // Game controller to manage states
    Controller gameController;

    public static void main(String[] args) {
        PApplet.main("com.soundGame.App");
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        println("Setup started");
        // Initialize the melody manager and add midi files
        addMidiFiles();
        melodyManager.start(2); // Start playing the second melody

        // Initialize the game controller and set the initial state
        gameController = new Controller(this);
        println("Setup completed");
    }

    // Add all midi files to the melody manager
    public void addMidiFiles() {
        for (String midiFile : midiFiles) {
            melodyManager.addMidiFiles(filePath + midiFile + ".mid");
            println("Added MIDI file: " + midiFile);
        }
    }

    public void draw() {
        background(255);
        // Play melodies
        melodyManager.playMelodies(); // This will now correctly call playMelodies
       // println("Draw loop executed");

        // Update and display the current game state
        if (gameController != null) {
            gameController.update();
            gameController.display();
        } else {
            println("Game controller is null");
        }

        // Check if the current state is PlayState and whether to switch states
        if (gameController != null && gameController.currentState instanceof PlayState) {
            PlayState playState = (PlayState) gameController.currentState;
            if (playState.someConditionToSwitchState()) {
                gameController.currentState.reset();  // Reset the current state before switching
                gameController.setState(gameController.gameOverState);  // Switch to the GameOverState
            }
        }
    }

    // Delegate key press event to the current game state
    public void keyPressed() {
        gameController.keyPressed();
    }

    // Delegate mouse events to the current game state
    public void mousePressed() {
        gameController.mousePressed();
    }

    public void mouseDragged() {
        gameController.mouseDragged();
    }

    public void mouseReleased() {
        gameController.mouseReleased();
    }
}
