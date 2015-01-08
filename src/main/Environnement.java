package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

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
    
    public void doIt() {
        // remember all agents with new references for the next turns of the party
        ArrayList<Agent> nextTurn = new ArrayList<Agent>(this.agents);
        this.agents.remove(0);
        Random random = new Random();
        //System.out.println(random.nextInt(this.agents.size()) - 2);
        /*
        while (this.agents.size() > 0) {
            Agent agent = this.agents.get(random.nextInt(this.agents.size()) - 1);
            int[] newPosition = findAvailablePosition(agent.getPosX(),  agent.getPosY());
            if (newPosition != null) { // the agent is able to move in an other box
                // update the agent localisation values
                agent.setPosX(newPosition[0]);
                agent.setPosY(newPosition[1]);
                // move the agent in the grid
                this.grid[agent.getPosX()][agent.getPosY()] = agent;
                // clear old position
                this.grid[agent.getPosX()][agent.getPosY()] = null;
            }
        }
        */
    }
    
    /**
     * Find a random free position (without any agent) into the grid.
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
    

    /*
    public int[] findAvailablePosition(int posX, int posY) {
        if (this.grid.length > 1 | this.grid[0].length > 1) {
            int[] result = new int[2];
            // values will be randomised
            int randomX = -666;
            int randomY = -666;
            Random random = new Random();

            ArrayList<ArrayList<E>> positionSets = new ArrayList<ArrayList<E>>();
            positionSets.add(new int[0, 0]);
            // a box in a grid is surrounded at least by 8 boxes
            int maximumAttempts = 8;
            while (maximumAttempts > 0) {
                // a random number between [-1, 1]
                randomX = (random.nextInt(3)) - 1;
                randomY = (random.nextInt(3)) - 1;
                if ( ((posX + randomX) >= 0) && ((posX + randomX) < this.grid.length)
                    & ((posY + randomY) >= 0) && ((posY + randomY) < this.grid[0].length)) {
                        if (this.grid[posX + randomX][posY + randomY] == null) {
                            result[0] = posX + randomX;
                            result[1] = posY + randomY;
                            return result;
                        }
                    }
                maximumAttempts--;
            }
        }
        return null;
    }
    */
    
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