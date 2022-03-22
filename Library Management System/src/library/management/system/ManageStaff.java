package library.management.system;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
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

public class ManageStaff implements ActionListener,MenuListener{
    Connection con;
    Statement stmt;
    
    String[] userType = {"User", "No"};
    JFrame frame = new JFrame();
    JMenuBar menuBar;
    JMenu show, insert, update, delete;
    JTable result_container;
    DefaultTableModel tableModel;
    JScrollPane scroll_result;
    JLabel newid, newname, newmobile, newisadmin, newdesignation, newpassword, idorname;
    JTextField input_new_id, input_new_name, input_new_mobile;
    JPasswordField input_new_password;
    JRadioButton rdb_yes, rdb_no;
    JComboBox designation;
    ButtonGroup rdb_isadmin;
    JButton insert_new, delete_selected, show_result;
    ManageStaff()
    {
        newid = new JLabel("ID: ");
        idorname = new JLabel("ID/Name");
        newname = new JLabel("Name:");
        newmobile = new JLabel("Mobile: ");
        newisadmin = new JLabel("Is Admin: ");
        newdesignation = new JLabel("Designation: ");
        newpassword = new JLabel("Password: ");
        newid.setFont(new Font("Ariel", Font.BOLD, 14));
        idorname.setFont(new Font("Ariel", Font.BOLD, 14));
        newname.setFont(new Font("Ariel", Font.BOLD, 14));
        newmobile.setFont(new Font("Ariel", Font.BOLD, 14));
        newisadmin.setFont(new Font("Ariel", Font.BOLD, 14));
        newdesignation.setFont(new Font("Ariel", Font.BOLD, 14));
        newpassword.setFont(new Font("Ariel", Font.BOLD, 14));
        String[] designation_list = {"Junior", "Senior"};
        rdb_isadmin = new ButtonGroup();
        input_new_id = new JTextField();
        input_new_name = new JTextField();
        input_new_mobile = new JTextField();
        rdb_yes = new JRadioButton("Yes");
        rdb_no = new JRadioButton("No");
        rdb_isadmin.add(rdb_yes);
        rdb_isadmin.add(rdb_no);
        designation = new JComboBox(designation_list);
        input_new_password = new JPasswordField();
        input_new_id.setFont(new Font("Ariel", Font.BOLD, 14));
        input_new_name.setFont(new Font("Ariel", Font.BOLD, 14));
        input_new_mobile.setFont(new Font("Ariel", Font.BOLD, 14));
        rdb_yes.setFont(new Font("Ariel", Font.BOLD, 14));
        rdb_no.setFont(new Font("Ariel", Font.BOLD, 14));
        designation.setFont(new Font("Ariel", Font.BOLD, 14));
        input_new_password.setFont(new Font("Ariel", Font.BOLD, 14));
        
        insert_new = new JButton("Insert");
        insert_new.setFont(new Font("Ariel", Font.BOLD, 14));
        delete_selected= new JButton("Delete Secleted");
        delete_selected.setFont(new Font("Ariel", Font.BOLD, 14));
        show_result= new JButton("Show");
        show_result.setFont(new Font("Ariel", Font.BOLD,14));
        
        
        
        insert_new.setBounds(430, 225, 100, 25);
        delete_selected.setBounds(400, 55, 150, 30);
        show_result.setBounds(400, 55, 150, 30);
        
        menuBar = new JMenuBar();
        
        show = new JMenu("Show");
        insert = new JMenu("Insert");
        update = new JMenu("Update");
        delete = new JMenu("Delete");
        
        show_result.addActionListener(this);
        insert.addMenuListener(this);
        update.addMenuListener(this);
        delete.addMenuListener(this);
        insert_new.addActionListener(this);
        delete_selected.addActionListener(this);
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Mobile");
        tableModel.addColumn("Is Admin");
        tableModel.addColumn("Designation");
        tableModel.addColumn("Password");
        result_container = new JTable(tableModel);
        result_container.getColumnModel().getColumn(1).setPreferredWidth(100);
        result_container.setFont(new Font("Ariel", Font.BOLD, 14));
        scroll_result = new JScrollPane (result_container);
        scroll_result.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        scroll_result.setBounds(25, 300, 930, 400);
        
        newid.setVisible(false);
        idorname.setVisible(false);
        newname.setVisible(false);
        newmobile.setVisible(false);
        newisadmin.setVisible(false);
        newdesignation.setVisible(false);
        newpassword.setVisible(false);
        input_new_id.setVisible(false);
        input_new_name.setVisible(false);
        input_new_mobile.setVisible(false);
        rdb_yes.setVisible(false);
        rdb_no.setVisible(false);
        designation.setVisible(false);
        input_new_password.setVisible(false);
        insert_new.setVisible(false);
        delete_selected.setVisible(false);
        show_result.setVisible(false);
        
        rdb_no.setSelected(true);
        
        menuBar.add(show);
        menuBar.add(insert);
        menuBar.add(update);
        menuBar.add(delete);
        frame.add(scroll_result);
        frame.add(newid);
        frame.add(idorname);
        frame.add(newname);
        frame.add(newmobile);
        frame.add(newisadmin);
        frame.add(newdesignation);
        frame.add(newpassword);
        frame.add(input_new_id);
        frame.add(input_new_name);
        frame.add(input_new_mobile);
        frame.add(rdb_yes);
        frame.add(rdb_no);
        frame.add(designation);
        frame.add(input_new_password);
        frame.add(insert_new);
        frame.add(delete_selected);
        frame.add(show_result);
        
        frame.setJMenuBar(menuBar);
        //Connecting to database
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "root");
            stmt = con.createStatement();
        }
        catch(SQLException e){}
        
        frame.setSize(1000,800);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                HomePage.main(userType);
                frame.dispose();
            }
        });
        frame.setLayout(null);
        frame.setTitle("Library Management - Manage Staff");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == show_result)
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
            if(!ID.isEmpty())
            {
                try
                {
                    int id = Integer.parseInt(ID);
                    ResultSet rs = stmt.executeQuery("Select * from staff_details where ID = " + id +"");
                    while(rs.next())
                    {
                         tableModel.insertRow(tableModel.getRowCount(), new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
                    }
                }
                catch(SQLException e1){}
            }
            else if(!Name.isEmpty())
            {
                try
                {
                    ResultSet rs = stmt.executeQuery("Select * from staff_details where Name = '" + Name +"' order by id");
                    while(rs.next())
                    {
                         tableModel.insertRow(tableModel.getRowCount(), new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
                    }
                }
                catch(SQLException e1){}
            }
        }
        else if(e.getSource() == insert_new)
        {
            String ID = input_new_id.getText();
            String Name = input_new_name.getText();
            String Mobile = input_new_mobile.getText();
            String Designation = String.valueOf(designation.getSelectedItem());
            String Password = String.valueOf(input_new_password.getPassword());
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
            else if(Mobile.contains("\"") || Mobile.contains("\'") || Mobile.contains("-") || Mobile.contains("#") || Mobile.contains(";") || Mobile.contains("&") || Mobile.contains("^") || Mobile.contains("(") || Mobile.contains(")"))
            {
                JOptionPane.showMessageDialog(null, "Invalid Characters in Mobile");
                input_new_mobile.setText("");
            }
            else if(Designation.contains("\"") || Designation.contains("\'") || Designation.contains("-") || Designation.contains("#") || Designation.contains(";") || Designation.contains("&") || Designation.contains("^") || Designation.contains("(") || Designation.contains(")"))
            {
                JOptionPane.showMessageDialog(null, "Invalid Characters in Designation");
                designation.setSelectedIndex(0);
            }
            else if(Password.contains("\"") || Password.contains("\'") || Password.contains("-") || Password.contains("#") || Password.contains(";") || Password.contains("&") || Password.contains("^") || Password.contains("(") || Password.contains(")"))
            {
                JOptionPane.showMessageDialog(null, "Invalid Characters in Designation");
                input_new_password.setText("");
            }
            
            if(!ID.isEmpty() && !Name.isEmpty() && !Mobile.isEmpty() && !Designation.isEmpty() && !Password.isEmpty())
            {
                try
                {
                    int id = Integer.parseInt(ID);
                    String user_admin_or_not = "No";
                    if(rdb_yes.isSelected())
                    {
                        user_admin_or_not = "Yes";
                    }
                    stmt.executeUpdate("Insert into staff_details values(" + id +" , '" + Name +"', '" + Mobile + "', '" + user_admin_or_not + "', '" + Designation + "', '" + Password + "')");
                }
                catch(SQLException e1){}
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Fields can not be empty");
            }
            
            tableModel.setRowCount(0);
            printFullTable();
            
            input_new_id.setText("");
            input_new_name.setText("");
            input_new_mobile.setText("");
            rdb_no.setSelected(true);
            designation.setSelectedIndex(0);
            input_new_password.setText("");
        }
        
        else if(e.getSource() == delete_selected)
        {
            int[] index = result_container.getSelectedRows();
            for(int i = 0; i < index.length; i++)
            {
                try
                {
                    stmt.executeUpdate("Delete from staff_details where ID = " + result_container.getValueAt(index[i], 0) + "");
                }
                catch(SQLException e2)
                {}
            }
            tableModel.setRowCount(0);
            printFullTable();
        }
    }
    
    public void menuSelected(MenuEvent me){
        
        if(me.getSource() == show)
        {
            scroll_result.setBounds(25, 100, 930, 400);
            newid.setVisible(false);
            idorname.setVisible(true);
            newname.setVisible(false);
            newmobile.setVisible(false);
            newisadmin.setVisible(false);
            newdesignation.setVisible(false);
            newpassword.setVisible(false);
            input_new_id.setVisible(true);
            input_new_name.setVisible(false);
            input_new_mobile.setVisible(false);
            rdb_yes.setVisible(false);
            rdb_no.setVisible(false);
            designation.setVisible(false);
            input_new_password.setVisible(false);
            insert_new.setVisible(false);
            delete_selected.setVisible(false);
            show_result.setVisible(true);
            
            tableModel.setRowCount(0);
            idorname.setBounds(310, 15, 100, 20);
            
            show.setSelected(true);
            insert.setSelected(false);
            update.setSelected(false);
            delete.setSelected(false);
            
            printFullTable();
        }
        
        else if(me.getSource() == insert)
        {
            scroll_result.setBounds(25, 300, 930, 400);
            show.setSelected(false);
            insert.setSelected(true);
            update.setSelected(false);
            delete.setSelected(false);
            
            newid.setVisible(true);
            newname.setVisible(true);
            newmobile.setVisible(true);
            newisadmin.setVisible(true);
            newdesignation.setVisible(true);
            newpassword.setVisible(true);
            input_new_id.setVisible(true);
            input_new_name.setVisible(true);
            input_new_mobile.setVisible(true);
            rdb_yes.setVisible(true);
            rdb_no.setVisible(true);
            designation.setVisible(true);
            input_new_password.setVisible(true);
            insert_new.setVisible(true);
            delete_selected.setVisible(false);
            
            newid.setBounds(310, 15, 100, 20);
            newname.setBounds(310, 45, 100, 20);
            newmobile.setBounds(310, 105, 100, 20);
            newisadmin.setBounds(310, 135, 100, 20);
            newdesignation.setBounds(310, 165, 100, 20);
            newpassword.setBounds(310, 195, 100, 20);
        
            input_new_id.setBounds(450, 15, 150, 20);
            input_new_name.setBounds(450, 45, 150, 20);
            input_new_mobile.setBounds(450, 105, 150,20);
            rdb_yes.setBounds(450, 135, 70, 20);
            rdb_no.setBounds(530, 135, 60, 20);
            designation.setBounds(450, 165, 150,20);
            input_new_password.setBounds(450, 195, 150,20);
        
            input_new_id.grabFocus();
        }
        else if(me.getSource() == update)
        {
            scroll_result.setBounds(25, 300, 930, 400);
            
            newid.setVisible(false);
            newname.setVisible(false);
            newmobile.setVisible(false);
            newisadmin.setVisible(false);
            newdesignation.setVisible(false);
            newpassword.setVisible(false);
            input_new_id.setVisible(false);
            input_new_name.setVisible(false);
            input_new_mobile.setVisible(false);
            rdb_yes.setVisible(false);
            rdb_no.setVisible(false);
            designation.setVisible(false);
            input_new_password.setVisible(false);
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
            scroll_result.setBounds(25, 300, 930, 400);
            
            newid.setVisible(false);
            newname.setVisible(false);
            newmobile.setVisible(false);
            newisadmin.setVisible(false);
            newdesignation.setVisible(false);
            newpassword.setVisible(false);
            input_new_id.setVisible(false);
            input_new_name.setVisible(false);
            input_new_mobile.setVisible(false);
            rdb_yes.setVisible(false);
            rdb_no.setVisible(false);
            designation.setVisible(false);
            input_new_password.setVisible(false);
            insert_new.setVisible(false);
            delete_selected.setVisible(true);
            
            show.setSelected(false);
            insert.setSelected(false);
            update.setSelected(false);
            delete.setSelected(true);
        }
    }

    public void menuCanceled(MenuEvent e1) {}
        
    public void menuDeselected(MenuEvent e2) {}
    
    public void printFullTable(){
        
        try
            {
                 ResultSet rs = stmt.executeQuery("Select * from staff_details order by ID");
                 while(rs.next())
                 {
                     tableModel.insertRow(tableModel.getRowCount(), new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
                 }
            }
            catch(SQLException e)
            {
                
            }
    }
    
    public static void main(String[] args) {
        
        ManageStaff obj = new ManageStaff();
//        obj.userType[0] = args[0];
//        obj.userType[1] = args[1];
        
    }
}
