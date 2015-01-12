package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import controller.Main;
import model.Environnement;
import model.agent.Agent;
import model.agent.Shark;

public class MainFrame extends JFrame implements Observer {
    
    public Environnement environnement;
    
    public JPanel gridPanel;

    public MainFrame(Environnement environnement) {       
        // private variables
        this.environnement = environnement;
        setTitle("Multi-agents");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // set layout
        GridBagLayout gbLayout = new GridBagLayout();
        getContentPane().setLayout(gbLayout);
        GridBagConstraints c = new GridBagConstraints();

        this.gridPanel = new GridPanel(this.environnement.getGrid());
        c.gridx = 0;
        c.gridy = 1;
        add(this.gridPanel, c);
        
        JButton runButton = new JButton("Run");
        JButton nextButton = new JButton("next");
        c.gridx = 1;
        c.gridy = 1;

        runButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //nextButton.setEnabled(false);
                environnementRun();
            }
        });
        
        nextButton.addActionListener(new ActionListener() {
                        
            @Override
            public void actionPerformed(ActionEvent e) {
                environnementDoIt();
            }
        });
        c.gridx = 2;
        c.gridy = 1;
        this.add(runButton, c);
        c.gridx = 1;
        c.gridy = 1;
        this.add(nextButton, c);
        // listeners
        //...
        
        // validate container and subcomponents
        validate();
        pack();
        setVisible(true);
    }
    
    public void environnementRun() {
        this.environnement.run();
    }
    
    public void environnementDoIt() {
        this.environnement.doIt();
    }

    @Override
    public void update() {
        remove(this.gridPanel);
        // !!!!!!!!!! ne pas faire une nouvelle grille mais updater les composants
        this.gridPanel = new GridPanel(this.environnement.getGrid());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        add(this.gridPanel, c);
        validate();
    }
}
