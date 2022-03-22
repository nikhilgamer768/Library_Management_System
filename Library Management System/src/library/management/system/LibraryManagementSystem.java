package library.management.system;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LibraryManagementSystem implements ActionListener{

    JFrame frame = new JFrame();
    JLabel login, userid, password;
    JTextField input_userid;
    JPasswordField input_password;
    JButton submit;
    LibraryManagementSystem()
    {   
        login = new JLabel("Login");
        userid = new JLabel("User ID: ");
        password = new JLabel("Password: ");
        login.setFont(new Font("Ariel", Font.BOLD, 30));
        userid.setFont(new Font("Ariel", Font.BOLD, 20));
        password.setFont(new Font("Ariel", Font.BOLD, 20));

        input_userid = new JTextField();
        input_password = new JPasswordField();
        input_userid.setFont(new Font("Ariel", Font.PLAIN, 15));
        input_password.setFont(new Font("Ariel", Font.PLAIN, 15));

        submit = new JButton("Submit");
        submit.setFont(new Font("Ariel", Font.BOLD, 20));

        login.setBounds(170, 25, 150, 40);
        userid.setBounds(50, 120, 120, 25);
        password.setBounds(50, 170, 120, 25);
        input_userid.setBounds(180, 120, 170, 25);
        input_password.setBounds(180, 170, 170, 25);
        submit.setBounds(155, 250, 120, 35);
        
        submit.addActionListener(this);

        frame.add(login);
        frame.add(userid);
        frame.add(password);
        frame.add(input_userid);
        frame.add(input_password);
        frame.add(submit);
        
        frame.setSize(450, 450);
        frame.setTitle("Library Management System");
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        String login_id = input_userid.getText();
        String login_password = String.valueOf(input_password.getPassword());
        if(login_id.equals("") || login_password.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Enter User ID");
            input_userid.setText("");
            input_userid.grabFocus();
        }
        else if(login_password.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Enter Password");
            input_password.setText("");
            input_password.grabFocus();
        }
        if(login_id.contains("\"") || login_id.contains("\'") || login_id.contains("-") || login_id.contains("#") || login_id.contains(";") || login_id.contains("&") || login_id.contains("^") || login_id.contains("(") || login_id.contains(")"))
        {
            JOptionPane.showMessageDialog(null, "Invalid Characters in User id");
            input_userid.setText("");
        }
        else if(login_password.contains("\"") || login_password.contains("\'") || login_password.contains("-") || login_password.contains("#") || login_password.contains(";") || login_password.contains("&") || login_password.contains("^") || login_password.contains("(") || login_password.contains(")"))
        {
            JOptionPane.showMessageDialog(null, "Invalid Characters in Password");
            input_password.setText("");
        }
        else
        {
         Statement stmt;
         Connection con;
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "root");
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from staff_details where ID = " + login_id +" and password = '" + login_password + "'" );
            if(rs.next())
            {
                String[] result = {rs.getString(2), rs.getString(5)};
                HomePage.main(result);
                frame.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid User ID or Password");
            }
            
        }
        catch(SQLException e1)
        {}
        }

    }

    public static void main(String[] args) {
        new LibraryManagementSystem();
    }
    
}