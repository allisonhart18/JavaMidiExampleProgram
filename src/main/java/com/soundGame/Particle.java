/*
 * Coder: Allison Hart
 * 9/30/2024
 * Particle Parent class - holds all basic info for shapes
 * 
 * 
 */
package com.soundGame;

import java.util.ArrayList;
abstract class Particle {
    float x, y;
    float vx, vy; // Velocity
    float size;
    int color;
    App main;
    boolean isDragging = false;  // Track dragging state
    float offsetX, offsetY;  // Offset to track mouse position relative to the particle

    melodyManager melodyy = null;
    ArrayList<String> sounds;  // To access the sounds ArrayList from App

    Particle(App main_, float x_, float y_, float vx_, float vy_, float size_, int color_) {
        main = main_;
        x = x_;
        y = y_;
        vx = vx_;
        vy = vy_;
        size = size_;
        color = color_;

        melodyy = main_.getmelodyManager();  // Get the melodyManager instance
        sounds = new ArrayList<>();  // Initialize sounds list to avoid null pointer
        for (String sound : main_.getMidiFiles()) {
            sounds.add(sound);  // Add sound names from the main class
        }
    }

    void playSound(int soundIndex) {
        if (melodyy != null && soundIndex >= 0 && soundIndex < sounds.size()) {
            melodyy.start(soundIndex);  // Start playing the sound at the given index
        } else {
            App.println("Error: Invalid sound index or melodyManager not initialized.");
        }
    }

    void move() {
        // Only move if not being dragged
        if (!isDragging) {
            x += vx;
            y += vy;
            checkEdges();
        }
    }

    // Modify checkEdges to include the bounce sound effect
    void checkEdges() {
        boolean bounced = false;  // Track if a bounce occurred

        // Check for collision with the left or right edges
        if (x <= 0 || x >= main.width) {
            vx *= -1;  // Reverse horizontal velocity
            bounced = true;
        }
        // Check for collision with the top or bottom edges
        if (y <= 0 || y >= main.height) {
            vy *= -1;  // Reverse vertical velocity
            bounced = true;
        }

        // Play the bounce sound (index 0) if a collision occurred
        if (bounced) {
            playSound(0);
        }
    }

    boolean isMouseOver() {
        return App.dist(main.mouseX, main.mouseY, x, y) < size / 2;
    }

    void mousePressed() {
        if (isMouseOver()) {
            isDragging = true;
            // Correct the offset calculation by subtracting the current position from the mouse position
            offsetX = main.mouseX - x;
            offsetY = main.mouseY - y;

            main.noStroke();
            color = main.color(main.random(255), main.random(255), main.random(255));
        }
    }

    void mouseDragged() {
        if (isDragging) {
            // Update position to follow the mouse, accounting for the offset
            x = main.mouseX - offsetX;
            y = main.mouseY - offsetY;
        }
    }

    void mouseReleased() {
        isDragging = false;
        checkIfDroppedInBox();  // Check if the particle is dropped inside the box
    }

    abstract void display();

    // Method to check if dropped in the box and play the bounce sound if true
    void checkIfDroppedInBox() {
        float boxX = 0, boxY = 0;
        float boxWidth = 100, boxHeight = 100;

        // Check if the particle is inside the box after release
        if (x >= boxX && x <= boxX + boxWidth && y >= boxY && y <= boxY + boxHeight) {
            App.println("Dropped in the box!");
            playSound(0);  // Play the bounce sound (index 0) when dropped into the box
        }
    }
}
