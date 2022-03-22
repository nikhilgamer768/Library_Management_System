package library.management.system;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class HomePage implements ActionListener{
    
    String[] userType = {"User", "No"};
    JFrame frame = new JFrame();
    JLabel welcome;
    JButton logout, issueBook, bookReturn, report, manageStaff, manageStudent, manageBook;
    HomePage()
    {
        welcome = new JLabel();
        welcome.setFont(new Font("Ariel", Font.BOLD, 30));
        welcome.setBounds(0, 10, 500, 50);
        welcome.setHorizontalAlignment(JLabel.CENTER);
        
        issueBook = new JButton("Issue/Reissue");
        bookReturn = new JButton("Return Book");
        report = new JButton("Reports");
        manageBook = new JButton("Manage Book");
        manageStudent = new JButton("Manage Student");
        manageStaff = new JButton("Manage Staff");
        logout = new JButton("Logout");
        
        issueBook.setFont(new Font("Ariel", Font.BOLD, 20));
        bookReturn.setFont(new Font("Ariel", Font.BOLD, 20));
        report.setFont(new Font("Ariel", Font.BOLD, 20));
        manageBook.setFont(new Font("Ariel", Font.BOLD, 20));
        manageStudent.setFont(new Font("Ariel", Font.BOLD, 20));
        manageStaff.setFont(new Font("Ariel", Font.BOLD, 20));
        logout.setFont(new Font("Ariel", Font.BOLD, 20));
        
        issueBook.setBounds(50, 110, 200, 30);
        bookReturn.setBounds(50, 150, 200, 30);
        report.setBounds(50, 190, 200, 30);
        manageBook.setBounds(50, 230, 200, 30);
        manageStudent.setBounds(50, 270, 200, 30);
        manageStaff.setBounds(50, 310, 200, 30);
        logout.setBounds(50, 420, 150, 30);
        
        logout.addActionListener(this);
        issueBook.addActionListener(this);
        bookReturn.addActionListener(this);
        manageStaff.addActionListener(this);
        manageStudent.addActionListener(this);
        report.addActionListener(this);
        manageBook.addActionListener(this);
        
        frame.add(welcome);
        frame.add(logout);
        frame.add(issueBook);
        frame.add(bookReturn);
        frame.add(manageStaff);
        frame.add(manageStudent);
        frame.add(report);
        frame.add(manageBook);
        
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setTitle("Library Management");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == logout)
        {
            LibraryManagementSystem.main(null);
            frame.dispose();
        }
        else if(e.getSource() == issueBook)
        {
            Reports.main(userType);
            frame.dispose();
        }
        else if(e.getSource() == bookReturn)
        {
            BookReturn.main(userType);
            frame.dispose();
        }
        else if(e.getSource() == manageStaff)
        {
            ManageStaff.main(userType);
            frame.dispose();
        }
        else if(e.getSource() == manageStudent)
        {
            ManageStudent.main(userType);
            frame.dispose();
        }
        else if(e.getSource() == report)
        {
            Reports.main(userType);
            frame.dispose();
        }
        else if(e.getSource() == manageBook)
        {
            ManageBook.main(userType);
            frame.dispose();
        }
    }
    
    public static void main(String args[]) {
    
        HomePage obj = new HomePage();
        obj.welcome.setText("Welcome " + args[0]);
        obj.userType[0] = args[0];
        obj.userType[1] = args[1];
        
    }
}
