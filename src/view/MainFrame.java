package view;

import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Multi-agents");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridBagLayout gbLayout = new GridBagLayout();
        getContentPane().setLayout(gbLayout);
        
        TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() { return 10; }
            public int getRowCount() { return 10;}
            public Object getValueAt(int row, int col) { return new Integer(row*col); }
        };
        JTable table = new JTable(dataModel);
        this.add(table);
        
        JButton runButton = new JButton("Run");
        this.add(runButton);
    }

    public static void launch() {

        // ensure that all UI updates are concurrency-safe
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame ex = new MainFrame();
                ex.setVisible(true);
            }
        });
    }
}
