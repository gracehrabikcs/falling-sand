/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectorientedfallingsand;

import java.awt.Color;

/**
 *
 * @author ghrabik
 */
public class Melt extends Relationship {
    // Constructor for Melt class that takes two Particle objects as parameters
    public Melt(Particle firstParticle, Particle secondParticle) {
      
        super(firstParticle, secondParticle);
        
        this.setNewFirstParticle(firstParticle);
        this.setNewSecondParticle(new Particle("Empty", Color.BLACK));
    }

    
}
