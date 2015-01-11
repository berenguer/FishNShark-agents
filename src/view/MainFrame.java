package view;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Environnement;

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
        // components
        this.gridPanel = new GridPanel(this.environnement.getGrid());
        this.add(gridPanel);
        
        JButton runButton = new JButton("Run");
        /*
        int count=0;
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
            }
        });
        */
        this.add(runButton);
        // listeners
        //...
        
        // validate container and subcomponents
        validate();
    }

    @Override
    public void update() {
        remove(this.gridPanel);
        this.gridPanel = new GridPanel(this.environnement.getGrid());
        add(this.gridPanel);
        repaint();
        validate();
    }
}
