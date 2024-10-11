/*
 * Coder: Allison Hart
 * 9/30/2024
 * PlayState class - holds bulk of logic, main reason game plays - allows you to drag shapes, keep track of points, check for collision, and change hue of shapes with different keys
 * 
 * 
 */

 package com.soundGame;

 import processing.core.*;
 import java.util.ArrayList;
 
 class PlayState extends GameState {
     ArrayList<Particle> particles; // List of particles
     int score;
     float boxX, boxY, boxSize; // Drop box properties
     Controller controller; // Reference to controller
     boolean gameEnded = false; // Flag to indicate the game has ended
 
     PlayState(App main, Controller controller) {
         super(main);
         this.controller = controller; // Store the controller reference
         
         particles = new ArrayList<>(); // Ensure particles is initialized
 
         // Initialize particles
         for (int i = 0; i < 7; i++) {
             particles.add(new circleSprinkle(main, main.random(main.width), main.random(main.height), main.random(-2, 2), main.random(-2, 2), 20, main.color(main.random(255), main.random(255), main.random(255))));
             particles.add(new squareSprinkle(main, main.random(main.width), main.random(main.height), main.random(-2, 2), main.random(-2, 2), 20, main.color(main.random(255), main.random(255), main.random(255))));
             particles.add(new triangleSprinkle(main, main.random(main.width), main.random(main.height), main.random(-2, 2), main.random(-2, 2), 20, main.color(main.random(255), main.random(255), main.random(255))));
         }
         
         score = 0;
 
         // Initialize drop box properties
         boxX = 50;
         boxY = 50;
         boxSize = 100;
     }
 
     public boolean someConditionToSwitchState() {
         // For example, if the score reaches 100
         return score >= 100;
     }
 
     @Override
     void update() {
         // Check if the game has reached 100 points using the condition method
         if (someConditionToSwitchState()) {
             controller.setState(controller.gameOverState); // Transition to GameOverState
             return; // Ensure that no further updates happen once game over is triggered
         }
     
         // Collision detection and update all particles
         for (int i = 0; i < particles.size(); i++) {
             Particle p = particles.get(i);
             p.move();
     
             // Check for collisions if the particle is a triangleSprinkle
             if (p instanceof triangleSprinkle) {
                 for (int j = i + 1; j < particles.size(); j++) {
                     Particle other = particles.get(j);
                     if (other instanceof triangleSprinkle) {
                        ((triangleSprinkle) p).resolveCollision((triangleSprinkle) other, 0); 
                    }
                    
                 }
             }
     
             p.display();
         }
     
         // Display the score
         main.fill(0);
         main.textSize(20);
         main.text("Score: " + score, 10, 20);
     
         // Display the box for scoring
         main.noStroke();
         main.fill(177, 156, 217);
         main.rect(boxX, boxY, boxSize, boxSize); // Use boxX, boxY for the box position
     }
     
     @Override
     void reset() {
         // Reset score and other necessary variables
         score = 0;
 
         // Reinitialize particles
         particles.clear(); // Clear existing particles
         for (int i = 0; i < 7; i++) {
             particles.add(new circleSprinkle(main, main.random(main.width), main.random(main.height), main.random(-2, 2), main.random(-2, 2), 20, main.color(main.random(255), main.random(255), main.random(255))));
             particles.add(new squareSprinkle(main, main.random(main.width), main.random(main.height), main.random(-2, 2), main.random(-2, 2), 20, main.color(main.random(255), main.random(255), main.random(255))));
             particles.add(new triangleSprinkle(main, main.random(main.width), main.random(main.height), main.random(-2, 2), main.random(-2, 2), 20, main.color(main.random(255), main.random(255), main.random(255))));
         }
     }
 
     @Override
     void mousePressed() {
         if (!gameEnded) {
             for (Particle p : particles) {
                 p.mousePressed(); // Check for mouse press on particles
             }
         }
     }
 
     @Override
     void mouseDragged() {
         if (!gameEnded) {
             for (Particle p : particles) {
                 p.mouseDragged();
                // p.playSound(1); // Update particles during dragging
             }
            
         }
         
     }
 
     @Override
     void mouseReleased() {
         if (!gameEnded) {
             for (Particle p : particles) {
                 // Check if the particle is inside the box before ending the drag
                 if (p.isDragging && isInsideBox(p)) {
                     score += 10; // Award points
                     
                 }
                 p.mouseReleased(); // Stop dragging after the check
             }
             
         }
     }
 
     @Override
void keyPressed() {
    if (main.key == 'b' || main.key == 'B') {
        for (Particle p : particles) {
            if (p instanceof circleSprinkle) {
                int blue = (p.color & 0xFF); // Extract blue component
                blue = App.min(blue + 25, 255); // Increase blue, max 255
                p.color = (p.color & 0xFFFFFF00) | blue;
                p.playSound(1); // Play 'rightPlace' sound
            }
        }
    }

    if (main.key == 'r' || main.key == 'R') {
        for (Particle p : particles) {
            if (p instanceof triangleSprinkle) {
                int red = (p.color >> 16) & 0xFF; // Extract red component
                red = App.min(red + 25, 255); // Increase red, max 255
                p.color = (p.color & 0xFF00FFFF) | (red << 16); // Update red
                p.playSound(1); // Play 'rightPlace' sound
            }
        }
    }

    if (main.key == 'g' || main.key == 'G') {
        for (Particle p : particles) {
            if (p instanceof squareSprinkle) {
                int green = (p.color >> 8) & 0xFF; // Extract green component
                green = App.min(green + 25, 255); // Increase green, max 255
                p.color = (p.color & 0xFFFF00FF) | (green << 8); // Update green
                p.playSound(1); // Play 'rightPlace' sound
            }
        }
    }
}

     
     // Helper method to check if a particle is inside the drop box
     boolean isInsideBox(Particle p) {
         return p.x > boxX && p.x < boxX + boxSize && p.y > boxY && p.y < boxY + boxSize;
         
     }
     public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
     @Override
     void mouseClicked() {
         // Not used in PlayState, but defined as part of the GameState
     }

    @Override
    void display() {
        
    }
 }
 