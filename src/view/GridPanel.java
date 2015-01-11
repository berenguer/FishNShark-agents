package view;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.agent.Agent;
import model.agent.Shark;

public class GridPanel extends JPanel {
    
    public Agent[][] grid;
    
    public JLabel[][] labelsGrid;
    
    public GridPanel(Agent[][] grid) {
        this.grid = grid;
        this.labelsGrid = new JLabel[this.grid.length][this.grid.length];
        setLayout(new GridLayout(this.grid.length, this.grid.length)); 

        this.labelsGrid = new JLabel[this.grid.length][this.grid.length];

        for (int row = 0; row < this.grid.length; row++) {
            for (int column = 0; column < this.grid.length; column++) {
                //this.add(new JLabel("huu"));
                if (this.grid[row][column] != null) {
                    if (this.grid[row][column].getClass().equals(Shark.class)) {
                        
                        JLabel jLabel = new JLabel();
                        jLabel.setIcon(new ImageIcon("images/shark-icon2.png"));
                        this.add(jLabel);
                    } else {
                        JLabel jLabel = new JLabel();
                        jLabel.setIcon(new ImageIcon("images/fish-icon.png"));
                        this.add(jLabel);
                    }
                } else {
                    JLabel jLabel = new JLabel();
                    jLabel.setIcon(new ImageIcon("images/blue-water-icon.png"));
                    this.add(jLabel);
                }
                
            }
        }
    }

}
