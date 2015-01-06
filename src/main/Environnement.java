package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Environnement {
    
    public Agent[][] grid;
    
    public int nbFish;
    
    public int nbShark;
    
    public int tour;
    
    public ArrayList<Agent> agents;

    public Environnement(int size, int nbFish, int nbShark) throws NumberOfAgentsExceedSizeException {
        super();
        try {
            if ((nbFish + nbShark) > (size * size)) {
                throw new NumberOfAgentsExceedSizeException();
            }
            else {
                this.grid = new Agent[size][size];
                for (int i=0;i<size;i++)
                {
                    for (int j=0;j<size;j++)
                    {
                        this.grid[i][j]= null;
                    }
                }
                this.nbFish = nbFish;
                this.nbShark = nbShark;
                this.tour = 0;
                this.agents = new ArrayList<Agent>(nbFish + nbShark);
            }
        }
        catch(NumberOfAgentsExceedSizeException ex) {
             System.err.println("Too much fishs for this grid !");
        }
    }
    
    /**
     * Find random a free position (without any agent) into the grid.
     * @return
     */
    public int[] findAvailablePosition() {
        int[] result = new int[2];
        
        // find random X and Y positions
        int posX_random = (int)Math.round(Math.random() * ( this.grid.length-1 ));
        int posY_random = (int)Math.round(Math.random() * ( this.grid.length-1 ));
        
        boolean positionAvailable = false;
        
        // find a random position free if still available
        while (!positionAvailable) {
            if (this.grid[posX_random][posY_random] == null) {
                result[0] = posX_random;
                result[1] = posY_random;
                return result;
                
            } else {
                posX_random = (int)Math.round(Math.random() * ( this.grid.length-1 ));
                posY_random = (int)Math.round(Math.random() * ( this.grid.length-1 ));
            }
        }
        return null;
    }
    
    public void initiateGrid() {
        int nbFish_count = nbFish;
        int nbShark_count = nbShark;

        // place agents into the grid
        while ( (nbFish_count | nbShark_count) > 0 ) {

            // random = 0 place a fish or random = 1 place a shark
            int fishOrShark = (int)Math.round(Math.random() * ( 1 ));
            
            Agent agent;
            int[] availablePosition = findAvailablePosition();
            int posX =  availablePosition[0];
            int posY =  availablePosition[1];
            
            // create a fish, put it the grid, and referenced it the lists of agents
            if ( (fishOrShark == 0) && (nbFish_count > 0) ) {
                agent = new Fish(posX, posY, 1, this);
                this.agents.add(agent);
                this.grid[posX][posY] = agent;
                nbFish_count--;
            }
            // create a shark, put it the grid, and referenced it the lists of agents
            else if ( (fishOrShark == 1) && (nbShark_count > 0) ) {
                agent = new Shark(posX, posY, 3, this);
                this.agents.add(agent);
                this.grid[posX][posY] = agent;
                nbShark_count--;
            }
        }
    }
}

class NumberOfAgentsExceedSizeException extends Exception {

    // parameterless Constructor
    public NumberOfAgentsExceedSizeException() {
    }

    // constructor that accepts a message
    public NumberOfAgentsExceedSizeException(String message) {
        super(message);
    }
}