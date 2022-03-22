package library.management.system;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java .awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Reports {

    String[] userType = {"User", "No"};
    JFrame frame = new JFrame();
    Reports()
    {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                HomePage.main(userType);
                frame.dispose();
            }
        });
        frame.setLayout(null);
        frame.setTitle("Library Management - Issue Book");
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        
        Reports obj = new Reports();
        obj.userType[0] = args[0];
        obj.userType[1] = args[1];
        
    }
}
