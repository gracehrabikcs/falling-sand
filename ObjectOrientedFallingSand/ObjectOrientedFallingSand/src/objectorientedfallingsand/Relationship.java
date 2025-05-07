/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectorientedfallingsand;

/**
 *
 * @author grace
 */
public class Relationship {
    
    // You will need four instance variables: firstParticle, secondParticle, newFirstParticle, and newSecondParticle.  
    // All four instance variables are Particle objects.   
    private Particle firstParticle;
    private Particle secondParticle;
    private Particle newFirstParticle;
    private Particle newSecondParticle;
    
    // Write a constructor that takes in two parameters, one for the firstParticle and one for the secondParticle.  
    // Use these values to set the firstParticle and secondParticle data members.

    public Relationship(Particle firstParticle, Particle secondParticle) {
        this.firstParticle = firstParticle;
        this.secondParticle = secondParticle;
        
        // To implement a swap, in the constructor, store the firstParticle in the newSecondParticle data member 
        // and the secondParticle in the newFirstParticle data member.
        this.newSecondParticle = firstParticle;
        this.newFirstParticle = secondParticle;
    }

    public Particle getFirstParticle() {
        return firstParticle;
    }

    public Particle getSecondParticle() {
        return secondParticle;
    }

    public Particle getNewFirstParticle() {
        return newFirstParticle;
    }

    public Particle getNewSecondParticle() {
        return newSecondParticle;
    }

    public void setFirstParticle(Particle firstParticle) {
        this.firstParticle = firstParticle;
    }

    public void setSecondParticle(Particle secondParticle) {
        this.secondParticle = secondParticle;
    }

    public void setNewFirstParticle(Particle newFirstParticle) {
        this.newFirstParticle = newFirstParticle;
    }

    public void setNewSecondParticle(Particle newSecondParticle) {
        this.newSecondParticle = newSecondParticle;
    }
    
    
    
}
