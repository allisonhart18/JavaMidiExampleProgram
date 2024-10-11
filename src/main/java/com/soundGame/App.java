/*
 * coder - Allison Hart but lots of help from class videos
 * 10/10/204
 * Description: manages the game's setup, states, and sound playback using MIDI files
 * 
 * When r,g, and b are pressed a sound is made
 */
package com.soundGame;

import processing.core.*;

public class App extends PApplet {
    static String filePath = "mid" + System.getProperty("file.separator"); // Path to the midi file folder

    // Names of midi files used in the game
    String[] midiFiles = {"bounce", "rightPlace", "grab", "circle", "deep", "startend"};

    // Melody manager for handling midi
    melodyManager melodyManager = new melodyManager();

    // Game controller to manage states
    Controller gameController;

    public static void main(String[] args) {
        App.main("com.soundGame.App");
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        println("Setup started");
        // Initialize the melody manager and add midi files
        addMidiFiles();
        melodyManager.start(5); // Start playing the opening melody

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

    // Return the list of MIDI files (this method will be used by particles)
    public String[] getMidiFiles() {
        return midiFiles;
    }

    public void draw() {
        background(255);
        // Play melodies
        melodyManager.playMelodies(); // This will now correctly call playMelodies

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
                playSound(5);  // Play the "startend" sound when switching to GameOverState
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

    // Method to access the melodyManager
    public melodyManager getmelodyManager() {
        return melodyManager;
    }

    // Method to play a specific sound by index
    public void playSound(int soundIndex) {
        if (melodyManager != null && soundIndex >= 0 && soundIndex < midiFiles.length) {
            melodyManager.start(soundIndex);  // Play the sound at the given index
        } else {
            println("Error: Invalid sound index or melodyManager not initialized.");
        }
    }
}
