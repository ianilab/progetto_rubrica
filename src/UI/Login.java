package UI;

import Logic.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private static JTextField usernameField;
    private static JPasswordField passwordField;
    public Login(){
        super("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // Create a panel to hold the login form components
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loginButtonAction();
            }
        });

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        add(loginPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void loginButtonAction(){
        if(Utente.login(usernameField.getText(),new String(passwordField.getPassword())))
            this.dispose();
    }
    public static void loginError(){
        JOptionPane.showMessageDialog(null, "Username e/o password invalidi","Erorr",
                JOptionPane.ERROR_MESSAGE);
        usernameField.setText("");
        passwordField.setText("");
    }



}
