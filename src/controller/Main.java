package controller;

import java.awt.EventQueue;
import java.util.Collections;
import java.util.Timer;

import view.MainFrame;
import model.Environnement;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // initiate model
                    Environnement env = new Environnement(16, 4, 6);
                    env.initiateGrid();

                    // initiate view
                    MainFrame view = new MainFrame(env);
                    
                    // attach view as observer of the model
                    env.attach(view);
                    view.setVisible(true);
                    Collections.shuffle(env.agents);
                    
                    System.out.println("Number of agents : "
                            + env.agents.size());
                    System.out.println("Size of the grid : " + env.grid.length
                            + " x " + env.grid.length);
                    System.out.println(env.toString());

                    
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
