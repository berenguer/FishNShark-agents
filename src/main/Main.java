package main;

import java.util.Arrays;
import java.util.Collections;

public class Main {
    
    public static void main(String[] args) {
        try {
            Environnement env = new Environnement(20 , 4, 4);
            env.initiateGrid();
            Collections.shuffle(env.agents);
            System.out.println(">>>"+env.agents.size());
        } catch (NumberOfAgentsExceedSizeException e) {
            e.printStackTrace();
        }

    }
    
    

}
