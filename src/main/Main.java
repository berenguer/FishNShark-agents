package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        try {
            Environnement env = new Environnement(6, 3, 2);
            env.initiateGrid();
            
            Collections.shuffle(env.agents);
            System.out.println("Number of agents : "+ env.agents.size());
            System.out.println("Size of the grid : "+ env.grid.length + " x " + env.grid.length);
            System.out.println(env.toString());
            
            // run party
            int turns = 6;
            for (int i = turns; i > 0; i--) {
                env.doIt();
                System.out.println(env.toString());
            }
            
        } catch (NumberOfAgentsExceedSizeException e) {
            e.printStackTrace();
        }

    }
    
    

}
