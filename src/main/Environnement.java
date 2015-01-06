package main;

import java.util.ArrayList;

public class Environnement {
    
    public int[][] grid;
    
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
                this.grid = new int[size][size];
                for (int i=0;i<size;i++)
                {
                    for (int j=0;j<size;j++)
                    {
                        this.grid[i][j]= -1;
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
    
    public void initiateGrid() {
        int nbFish_count = nbFish;
        int nbShark_count = nbShark;
        
        
        
        int debugCount = 0;
        
        while ((nbFish_count | nbShark_count) > 0) {
            int posX_random = (int)Math.round(Math.random() * ( this.grid.length-1 ));
            int posY_random = (int)Math.round(Math.random() * ( this.grid.length-1 ));
            
            // random 0 place a fish, and for the value 1 place a shark
            int fishOrShark = (int)Math.round(Math.random() * ( 1 ));
            
            if ((fishOrShark == 0) && (nbFish_count > 0) && (this.grid[posX_random][posY_random] == -1)) {
                System.out.println("0");
                Fish fish = new Fish(posX_random, posY_random, 3, this);
                this.agents.add(fish);
                nbFish_count--;
            }
            else if ((fishOrShark == 1) && (nbShark_count > 0) && (this.grid[posX_random][posY_random] == -1)) {
                System.out.println("1");
                Shark shark = new Shark(posX_random, posY_random, 3, this);
                this.agents.add(shark);
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