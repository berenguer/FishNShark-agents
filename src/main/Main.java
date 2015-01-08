package main;

import java.util.Arrays;
import java.util.Collections;

public class Main {
    
    public static void main(String[] args) {
        try {
            Environnement env = new Environnement(3, 4, 4);
            env.initiateGrid();
            
            Collections.shuffle(env.agents);
            System.out.println("Number of agents : "+ env.agents.size());
            System.out.println("Size of the grid : "+ env.grid.length + " x " + env.grid.length);
            
            // A SUPPRIMER !!
            env.grid[1][1] = null;
            
            for (int i = 0; i < env.grid.length; i++) {
                System.out.println();
                for (int j = 0; j <env.grid[i].length; j++) {
                    System.out.print(env.grid[i][j] + "\t");
                }
                
            }
            
            // position around
            //int[] freePositionAround = env.findAvailablePosition(1,  1);
            //System.out.println("\nPosition around x: " + freePositionAround[0] + " y: " + freePositionAround[1]);
            //env.doIt();
        } catch (NumberOfAgentsExceedSizeException e) {
            e.printStackTrace();
        }

    }
    
    

}
