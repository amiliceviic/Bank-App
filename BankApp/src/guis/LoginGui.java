package guis;

import db_objs.MyJDBC;
import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGui extends BaseFrame{
    public LoginGui() {
        super("Banking App Login");
    }

    @Override
    protected void addGuiComponents() {

        JLabel bankingAppLabel = new JLabel("Banking Application");
        bankingAppLabel.setBounds(0,20,super.getWidth(),40);
        bankingAppLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(bankingAppLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20,120,getWidth() - 30,24);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(20,160,getWidth() - 50,40);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 28));
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20,280,getWidth() - 50,24);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20,320,getWidth() - 50,40);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 28));
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(20,460,getWidth() - 50,40);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                User user = MyJDBC.validateLogin(username,password);

                if (user != null) {
                    LoginGui.this.dispose();

                    BankingAppGui bankingAppGui = new BankingAppGui(user);
                    bankingAppGui.setVisible(true);

                    JOptionPane.showMessageDialog(bankingAppGui,"Login Successfully!");
                }
                else {
                    JOptionPane.showMessageDialog(LoginGui.this, "Login failed...");
                }
            }
        });
        add(loginButton);

        JLabel registerLabel = new JLabel("<html><a href=\"#\">Don't have an account? Register Here</a></html>");
        registerLabel.setBounds(0,510,getWidth() - 10,30);
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginGui.this.dispose();
                new RegisterGui().setVisible(true);
            }
        });
        add(registerLabel);

    }
}
