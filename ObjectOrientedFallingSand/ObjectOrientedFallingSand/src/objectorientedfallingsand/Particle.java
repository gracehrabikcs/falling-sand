package objectorientedfallingsand;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Particle implements Comparable<Particle>
{
    // Fields go here.
    private String name;
    private Color color;
    private ArrayList<Movement> movements;
    private ArrayList<Relationship> relationships;
    
    // Constructor(s) go here.
    public Particle(String name, Color color)
    {
        this.name = name;
        this.color = color;
        this.movements = new ArrayList<>();
        this.relationships = new ArrayList<>();
    }

    // Methods go here.
    
    // adds the relationship object to the ArrayList.
    public void addRelationship (Relationship relationship)
    {
        relationships.add(relationship);
    }
    
    /* override the equals method inherited from the Object class.  
     This method should determine if a Particle object is equal to another one.  
     You can do this by seeing if their names are equal.
    */
    @Override
    public boolean equals (Object other)           
    {
        Particle otherParticle = (Particle) other;
        return this.name.equals(otherParticle.name);
    }
    
    // receives another Particle object as a parameter, and determines whether or not this Particle object has a relationship 
    // with the other Particle object.
    /*
    It does this by looking through the Relationship ArrayList and examining the otherParticle instance variable.  
    This method returns the Relationship object if the other particle is in the list.  
    If no relationship is defined, then this method should return null (no object).  
    You can use your equal method to determine if the two Particle objects are equal.
    */
    public Relationship getRelationshipWith (Particle otherParticle)
    {
        for (Relationship relationship : relationships) 
        {
            if (relationship.getSecondParticle().equals(otherParticle)) 
            {
                return relationship;
            }
         
        }
        return null;
    }
    // adds the Movement object to the ArrayList.
    public void addMovement(Movement movement)
    {
        movements.add(movement);
    }
    
    // returns true if the Particle can move (has Movement objects in the ArrayList).  Otherwise, returns false.
    public boolean isMoveable()
    {
        return !movements.isEmpty();
    }
    
    // returns a Movement object chosen at random from the ArrayList.  
    // If the ArrayList is empty, return a Movement object where the rowChange and columnChange are both set to 0.
    public Movement getRandomMovement()
    {
        if (movements.isEmpty())
        {
            return new Movement(0, 0);
         
        }
        else
        {
            Random rand = new Random();
            return movements.get(rand.nextInt(movements.size()));
            
        }
    }
    
    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    // The clone method does not take in any parameters, and it returns a new Particle object.  
    // This new Particle object should contain a copy of each of this Particle's data members.
    
    @Override
    public Particle clone()
    {
        Particle cloneParticle = new Particle(this.name, this.color);
        
        for (Movement movement : this.movements) 
        {
            cloneParticle.addMovement(movement);
        }
        
        for (Relationship relationship : this.relationships) 
        {
            cloneParticle.addRelationship(relationship);
        }
        return cloneParticle;
    }
    
    @Override
    public int compareTo(Particle otherParticle) {
        return this.name.compareTo(otherParticle.name);
    }
    
}
