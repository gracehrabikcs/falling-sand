/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectorientedfallingsand;

import java.awt.Color;

/**
 *
 * @author grace
 */
public class Freeze extends Relationship {
     // Constructor for Freeze class that takes two Particle objects as parameters
    public Freeze(Particle firstParticle, Particle secondParticle) {
      
        super(firstParticle, secondParticle);
        
     
        this.setNewFirstParticle(firstParticle); 
        this.setNewSecondParticle(new Particle("Ice", Color.CYAN)); 
    }


    
}
