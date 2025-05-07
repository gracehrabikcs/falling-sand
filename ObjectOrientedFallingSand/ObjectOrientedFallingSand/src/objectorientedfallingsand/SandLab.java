package objectorientedfallingsand;

import java.awt.*;
import java.util.*;


public class SandLab
{
    //do not add any more fields
    private Particle[][] grid;
    private SandDisplay display;
    public static final int ROWS = 120;
    public static final int COLUMNS = 80;

    public static void main(String[] args)
    {
        SandLab lab = new SandLab(ROWS, COLUMNS);
        lab.run();
    }

    public SandLab(int numRows, int numCols)
    {
        Particle[] particles = new Particle[7];
        // Create particle objects
        Particle empty = new Particle("Empty", Color.BLACK);
        Particle metal = new Particle("Metal", Color.GRAY);
        Particle water = new Particle("Water", Color.BLUE);
        Particle sand = new Particle("Sand", Color.YELLOW);
        Particle acid = new Particle("Acid", Color.GREEN);
        Particle ice = new Particle("Ice", Color.CYAN);
        Particle gas = new Particle("Gas", Color.DARK_GRAY);
                      
        // Store new particles in array
        particles[0] = empty;
        particles[1] = metal;
        particles[2] = water;
        particles[3] = sand;
        particles[4] = acid;
        particles[5] = ice;
        particles[6] = gas;
        
        Arrays.sort(particles);
        // All particle code goes above this line
        grid = new Particle[numRows][numCols];
        display = new SandDisplay("Falling Sand", numRows, numCols, particles);
       
         /*
        The grid should start empty.  
        Write a nested for loop to iterate through the grid 
        and store an "empty" Particle object in each cell of the grid.  
        Create a new empty Particle object for each cell of the grid.  
        This code goes in the SandLab constructor.  
        Move the line that creates the grid 
        (grid = new Particle[numRows][numCols];) 
        above the "display" line and write your nested for loop after the grid is created.
        */
        for (int row = 0; row < numRows; row++)
        {
            for (int col = 0; col < numCols; col++)
            {
                grid[row][col] = empty;
            }
        }
        
        // Create three movement objects        
        Movement left = new Movement(0, -1);
        Movement down = new Movement(1, 0);
        Movement right = new Movement(0, 1);
        Movement up = new Movement(-1, 0);
        
        // Add down movement object to sand and all three movement objects to water
        sand.addMovement(down);
        
        water.addMovement(left);
        water.addMovement(down);
        water.addMovement(right);
        
        acid.addMovement(down);
        
        ice.addMovement(down);
        
        gas.addMovement(up);
        gas.addMovement(left);
        gas.addMovement(right);
        
        // When sand interacts with empty, it swaps with it
        Relationship sandWithEmpty = new Relationship(sand, empty);
        sand.addRelationship(sandWithEmpty);
        
        // Similar to water
        Relationship sandWithWater = new Relationship(sand, water);
        sand.addRelationship(sandWithWater);
        
        // when water interacts with empty, it picks one of 3 movements at random
        Relationship waterWithEmpty = new Relationship(water, empty);
        water.addRelationship(waterWithEmpty);
        
        // when acid interacts with empty, it swaps with it
        Relationship acidWithEmpty = new Relationship(acid, empty);
        acid.addRelationship(acidWithEmpty);
        
        // when acid interacts with metal, it melts it
        Melt acidWithMetal = new Melt(acid, metal);
        acid.addRelationship(acidWithMetal);
        
        // when ice interacts with empty, it swaps with it
        Relationship iceWithEmpty = new Relationship(ice, empty);
        ice.addRelationship(iceWithEmpty);
        
        // when ice interacts with water, it freezes on it
        Freeze iceWithWater = new Freeze(ice, water);
        ice.addRelationship(iceWithWater);
        
        // when gas interacts with empty, it pick one of 3 movements at random
        Relationship gasWithEmpty = new Relationship(gas, empty);
        gas.addRelationship(gasWithEmpty);
        
        
        

        
     
        
    }

    //called when the user clicks on a location using the given tool
    private void locationClicked(int row, int col, Particle tool)
    {
        Particle particle = tool.clone();
        
        grid[row][col] = particle;
        
    }

    //copies each element of grid into the display
    public void updateDisplay()
    {
        Particle particle;
        
        for (int row = 0; row < ROWS; row++)
        {
            for (int column = 0; column < COLUMNS; column++)
            {
                particle = grid[row][column];
                display.setColor(row, column, particle.getColor());
                
            }
        }
    }

    //called repeatedly.
    //causes one random particle to maybe do something.
    public void step()
    {
        // Select a random location on the grid.
        int randomRow = (int)(Math.random() * ROWS);
        int randomCol = (int)(Math.random() * COLUMNS);
        
        // Store the Particle object in that location in a Particle variable.
        Particle particle  = grid[randomRow][randomCol];
        
        // Determine if that Particle is moveable.
        //If so, get a random Movement object from it.
        if (particle.isMoveable())
        {
            Movement movement = particle.getRandomMovement();
            
            // Determine if the new location described by the Movement object is on the grid.
            // If so, swap with the Particle at that location.
            int newRow = randomRow + movement.getRowChange();
            int newCol = randomCol + movement.getColumnChange();
                       
            if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLUMNS) 
            {
            // grid[randomRow][randomCol] = grid[newRow][newCol];
            // grid[newRow][newCol] = particle;
            
            // determine if the first particle has a relationship with the second one.  
            // If you haven't already, you may want to store a pointer to the second Particle object in a Particle variable.
            Particle otherParticle = grid[newRow][newCol];
            
            Relationship particleRelationship = particle.getRelationshipWith(otherParticle);
            
                // In an if statement, determine if the Relationship object is null.
                if (particleRelationship != null)
                {
                    Particle newFirstParticle = particleRelationship.getNewFirstParticle();
                    Particle newSecondParticle = particleRelationship.getNewSecondParticle();

                    // The Relationship object contains the particles you need to store in the first grid location and the second grid location.  
                    // Call the object's get methods to get the Particle objects and put them in the appropriate location.
                    grid[randomRow][randomCol] = newFirstParticle;
                    grid[newRow][newCol] = newSecondParticle;

                }
                // If the getRelationshipWith method returns null, then the first particle does not have a relationship with the second one, 
                // and we don't need to do anything. 
                // You do not need an else statement after the if.
               
            }
        }
    }  

    //do not modify
    public void run()
    {
        while (true)
        {
            for (int i = 0; i < display.getSpeed(); i++)
            {
                step();
            }

            updateDisplay();
            display.repaint();
            display.pause(1);  //wait for redrawing and for mouse
            int[] mouseLoc = display.getMouseLocation();

            if (mouseLoc != null)  //test if mouse clicked
            {
                locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
            }
        }
    }
}