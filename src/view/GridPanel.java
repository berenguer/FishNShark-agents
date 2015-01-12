package view;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Environnement;
import model.agent.Agent;
import model.agent.Shark;

public class GridPanel extends JPanel {

    public Agent[][] grid;
    
    public Agent[][] getGrid() {
        return grid;
    }

    public void setGrid(Agent[][] grid) {
        this.grid = grid;
    }

    public JLabel[][] labelsGrid;
    
    ImageIcon iconShark = new ImageIcon("images/shark-icon2.png");
    ImageIcon iconFish = new ImageIcon("images/fish-icon.png");
    ImageIcon iconWater = new ImageIcon("images/blue-water-icon.png");
    
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
                        jLabel.setIcon(iconShark);
                        this.add(jLabel);
                    } else {
                        JLabel jLabel = new JLabel();
                        jLabel.setIcon(iconFish);
                        this.add(jLabel);
                    }
                } else {
                    JLabel jLabel = new JLabel();
                    jLabel.setIcon(iconWater);
                    this.add(jLabel);
                }
                
            }
        }
    }
    
    public void updateGrid(Agent[][] newGrid) {
        
        for (int row = 0; row < this.grid.length; row++) {
            for (int column = 0; column < this.grid.length; column++) {
                //this.add(new JLabel("huu"));
                if (!this.grid[row][column].equals(newGrid[row][column])) {
                    if (this.grid[row][column] != null) {
                        if (this.grid[row][column].getClass().equals(Shark.class)) {
                            
                            JLabel jLabel = new JLabel();
                            jLabel.setIcon(iconShark);
                            this.add(jLabel);
                        } else {
                            JLabel jLabel = new JLabel();
                            jLabel.setIcon(iconFish);
                            this.add(jLabel);
                        }
                    } else {
                        JLabel jLabel = new JLabel();
                        jLabel.setIcon(iconWater);
                        this.add(jLabel);
                    }
                    labelsGrid[row][column].repaint(row, column, 1, 1);
                }
            }
        }
        this.grid = newGrid;
    }

}
