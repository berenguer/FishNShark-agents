package main;

import java.lang.reflect.Type;
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
    
    /**
     * Play one iteration/turn of the system.
     */
    public void doIt() {
        // order of agents in the turn is random
        Collections.shuffle(this.agents);
        Random random = new Random();
        //System.out.println(random.nextInt(this.agents.size()) - 2);

        for (int i = 0; i < this.agents.size(); i++) {
            System.out.println("Agent at "+String.valueOf(this.agents.get(i).getPosX()) + " : " + String.valueOf(this.agents.get(i).getPosY()));
            this.agents.get(i).action();
        }
        
        // update agents collections (births, dead)
        this.agents.clear();
        for (int x = 0; x < this.grid.length; x++) {
            for (int y = 0; y < this.grid[x].length; y++) {
                if (this.grid[x][y] != null) {
                    if (this.grid[x][y].getClass().getSuperclass().equals(Agent.class)) {
                        this.agents.add(this.grid[x][y]);
                    }
                }
            }
        }
    }
    
    /**
     * Find a random free position (without any agent) into the grid.
     * @return [posX, posY]
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
    
    /**
     * Search position of occurences of the target class, when located around the box at posX, posY.
     * Example, with the following grid
     * ---------------------
     *  Agent   null    Shark  
     *  null    Agent   null
     *  --------------------
     *  For the Agent at (0, 0) if target is set to "null" the result is [(0, 1), (1, 0)].
     *  For the Agent at (0, 0) if target is set to "Agent" the result is [(1, 1)].
     * @param posX x coordinate
     * @param posY y coordinate
     * @param target type of Object
     * @return arraylist with an array of the position (x, y) for each solution
     */
    public ArrayList<int[]> search(int posX, int posY, Type target) {
        
        if (this.grid.length > 1 | this.grid[0].length > 1) {
            // values before randomising
            int randomX = -666;
            int randomY = -666;
            Random random = new Random();
            // a random number between [-1, 1]
            randomX = (random.nextInt(3)) - 1;
            randomY = (random.nextInt(3)) - 1;

            ArrayList<int[]> positionSets = new ArrayList<int[]>();
            int[] values = new int[3];
            values[0] = -1;
            values[1] = 0;
            values[2] = 1;
            // x and y refer to a relative shift of posX, posY
            for (int x : values) {
                // check if the row at x + posX is inbound
                if ((x + posX >= 0) & (x + posX < this.grid.length)) {
                    for (int y : values) {
                        // check if the column at y + posY is inbound
                        if ((y + posY >= 0) & (y + posY < this.grid.length)) {
                            // omit the value exactly at posX, posY
                            if (!((x == 0) & (y == 0))) {
                                boolean saveIt = false;
                                if ((target == null) & (this.grid[x + posX][y + posY] == null)) {
                                    saveIt = true;
                                } else if ((this.grid[x + posX][y + posY] != null)) {
                                    if (this.grid[x + posX][y + posY].getClass().equals(target)) {
                                        saveIt = true;
                                    } 
                                }
                                
                                // save values
                                if (saveIt) {
                                    int[] position = new int[2];
                                    position[0] = x + posX;
                                    position[1] = y + posY;
                                    positionSets.add(position);
                                    saveIt = false;
                                }                                
                            }
                        }
                    } // for y
                } // for x
            }
            return positionSets;
 
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
            
            // create a fish, put it the grid, and referenced it in the list of agents
            if ( (fishOrShark == 0) && (nbFish_count > 0) ) {
                agent = new Fish(posX, posY, 1, this);
                this.agents.add(agent);
                this.grid[posX][posY] = agent;
                nbFish_count--;
            }
            // create a shark, put it the grid, and referenced it in the list of agents
            else if ( (fishOrShark == 1) && (nbShark_count > 0) ) {
                agent = new Shark(posX, posY, 3, 3, this);
                this.agents.add(agent);
                this.grid[posX][posY] = agent;
                nbShark_count--;
            }
        }
    }
    
    /**
     * Return a readable form of the grid with agents.
     */
    public String toString() {
        String res = "";
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                res += this.grid[i][j];
                res += "\t";
            }
            res += "\n";
        }
        return res;
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