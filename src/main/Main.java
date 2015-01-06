package main;

import java.util.Arrays;
import java.util.Collections;

public class Main {
    
    public static void main(String[] args) {
        try {
            Environnement env = new Environnement(8, 4, 8);
            env.initiateGrid();
            
            Collections.shuffle(env.agents);
            System.out.println("Number of agents : "+ env.agents.size());
            System.out.println("Size of the grid : "+ env.grid.length + " x " + env.grid.length);
            
            for (int i = 0; i < env.grid.length; i++) {
                System.out.println();
                for (int j = 0; j <env.grid[i].length; j++) {
                    System.out.print(env.grid[i][j] + "\t");
                }
                
            }
        } catch (NumberOfAgentsExceedSizeException e) {
            e.printStackTrace();
        }

    }
    
    

}
