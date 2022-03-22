package library.management.system;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java .awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ManageBinding implements ActionListener,MenuListener{
    Connection con;
    Statement stmt;
    
    String[] userType = {"User", "No"};
    JFrame frame = new JFrame();
    JMenuBar menuBar;
    JMenu show, insert, update, delete;
    JTable result_container;
    DefaultTableModel tableModel;
    JScrollPane scroll_result;
    JLabel newid, newName;
    JTextField input_new_id, input_new_name;
    JButton insert_new, delete_selected;
    ManageBinding()
    {
        newid = new JLabel("ID: ");
        newName = new JLabel("Name:");
        newid.setFont(new Font("Ariel", Font.BOLD, 14));
        newName.setFont(new Font("Ariel", Font.BOLD, 14));
        
        input_new_id = new JTextField();
        input_new_name = new JTextField();
        input_new_id.setFont(new Font("Ariel", Font.BOLD, 14));
        input_new_name.setFont(new Font("Ariel", Font.BOLD, 14));
        insert_new = new JButton("Insert");
        insert_new.setFont(new Font("Ariel", Font.BOLD, 14));
        delete_selected= new JButton("Delete Secleted");
        delete_selected.setFont(new Font("Ariel", Font.BOLD, 14));
        
        newid.setBounds(220, 15, 100, 20);
        newName.setBounds(220, 45, 100, 20);
        input_new_id.setBounds(300, 15, 150, 20);
        input_new_name.setBounds(300, 45, 150, 20);
        insert_new.setBounds(280, 110, 100, 25);
        delete_selected.setBounds(250, 55, 150, 30);
        
        menuBar = new JMenuBar();
        
        show = new JMenu("Show");
        insert = new JMenu("Insert");
        update = new JMenu("Update");
        delete = new JMenu("Delete");
        
        show.addMenuListener(this);
        insert.addMenuListener(this);
        update.addMenuListener(this);
        delete.addMenuListener(this);
        insert_new.addActionListener(this);
        delete_selected.addActionListener(this);
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        result_container = new JTable(tableModel);
        result_container.getColumnModel().getColumn(1).setPreferredWidth(100);
        result_container.setFont(new Font("Ariel", Font.BOLD, 14));
        scroll_result = new JScrollPane (result_container);
        scroll_result.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        scroll_result.setBounds(25, 150, 630, 450);
        
        newid.setVisible(false);
        newName.setVisible(false);
        input_new_id.setVisible(false);
        input_new_name.setVisible(false);
        insert_new.setVisible(false);
        delete_selected.setVisible(false);
        
        menuBar.add(show);
        menuBar.add(insert);
        menuBar.add(update);
        menuBar.add(delete);
        frame.add(scroll_result);
        frame.add(newid);
        frame.add(newName);
        frame.add(input_new_id);
        frame.add(input_new_name);
        frame.add(insert_new);
        frame.add(delete_selected);
        
        frame.setJMenuBar(menuBar);
        show.setSelected(true);
        
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                HomePage.main(userType);
                frame.dispose();
            }
        });
        frame.setLayout(null);
        frame.setTitle("Library Management - Manage Binding");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == insert_new)
        {
            String ID = input_new_id.getText();
            String Name = input_new_name.getText();
            if(ID.contains("\"") || ID.contains("\'") || ID.contains("-") || ID.contains("#") || ID.contains(";") || ID.contains("&") || ID.contains("^") || ID.contains("(") || ID.contains(")"))
            {
                JOptionPane.showMessageDialog(null, "Invalid Characters in ID");
                input_new_id.setText("");
            }
            else if(Name.contains("\"") || Name.contains("\'") || Name.contains("-") || Name.contains("#") || Name.contains(";") || Name.contains("&") || Name.contains("^") || Name.contains("(") || Name.contains(")"))
            {
                JOptionPane.showMessageDialog(null, "Invalid Characters in Name");
                input_new_name.setText("");
            }
            
            if(!ID.isEmpty() && !Name.isEmpty())
            {
                try
                {
                    stmt.executeUpdate("Insert into binding_details values('"+ID+"' , '" + Name +"')");
                }
                catch(SQLException e1){}
            }
            
            tableModel.setRowCount(0);
            printFullTable();
            
            input_new_id.setText("");
            input_new_name.setText("");
        }
        
        else if(e.getSource() == delete_selected)
        {
            int[] index = result_container.getSelectedRows();
            for(int i = 0; i < index.length; i++)
            {
                try
                {
                    stmt.executeUpdate("Delete from binding_details where ID = '" + result_container.getValueAt(index[i], 0) + "'");
                }
                catch(SQLException e2)
                {}
            }
            tableModel.setRowCount(0);
            printFullTable();
        }
    }
    
    public void menuSelected(MenuEvent me){
        
        //Connecting to database
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "root");
            stmt = con.createStatement();
        }
        catch(SQLException e){}
        
        if(me.getSource() == show)
        {
            newid.setVisible(false);
            newName.setVisible(false);
            input_new_id.setVisible(false);
            input_new_name.setVisible(false);
            insert_new.setVisible(false);
            delete_selected.setVisible(false);
            
            tableModel.setRowCount(0);
            
            show.setSelected(true);
            insert.setSelected(false);
            update.setSelected(false);
            delete.setSelected(false);
            
            printFullTable();
        }
        
        else if(me.getSource() == insert)
        {
            show.setSelected(false);
            insert.setSelected(true);
            update.setSelected(false);
            delete.setSelected(false);
            
            newid.setVisible(true);
            newName.setVisible(true);
            input_new_id.setVisible(true);
            input_new_name.setVisible(true);
            insert_new.setVisible(true);
            delete_selected.setVisible(false);
            
            input_new_id.grabFocus();
        }
        else if(me.getSource() == update)
        {
            newid.setVisible(false);
            newName.setVisible(false);
            input_new_id.setVisible(false);
            input_new_name.setVisible(false);
            insert_new.setVisible(false);
            delete_selected.setVisible(false);
        
            show.setSelected(false);
            insert.setSelected(false);
            update.setSelected(true);
            delete.setSelected(false);
            
            System.out.print("Update");
        }
        else if(me.getSource() == delete)
        {
            newid.setVisible(false);
            newName.setVisible(false);;
            input_new_id.setVisible(false);
            input_new_name.setVisible(false);
            insert_new.setVisible(false);
            delete_selected.setVisible(true);
        }
    }

    public void menuCanceled(MenuEvent e1) {}
        
    public void menuDeselected(MenuEvent e2) {}
    
    public void printFullTable(){
        
        try
            {
                 ResultSet rs = stmt.executeQuery("Select * from binding_details order by ID");
                 while(rs.next())
                 {
                     tableModel.insertRow(tableModel.getRowCount(), new Object[]{rs.getString(1), rs.getString(2)});
                 }
            }
            catch(SQLException e)
            {
                
            }
    }
    
    public static void main(String[] args) {
        
        ManageBinding obj = new ManageBinding();
//        obj.userType[0] = args[0];
//        obj.userType[1] = args[1];
        
    }
}
