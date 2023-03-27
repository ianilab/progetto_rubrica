package UI;

import Logic.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup extends JFrame {
    private static JTextField usernameField;
    private static JPasswordField passwordField;
    public Signup(){
        super("Aggiungi utente");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // Create a panel to hold the login form components
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        JButton signupBtn = new JButton("Aggiungi");
        signupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                salva();
            }
        });

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());
        loginPanel.add(signupBtn);

        add(loginPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void salva(){
        Utente u = new Utente(usernameField.getText(),new String(passwordField.getPassword()));
        u.save();
        this.dispose();
    }
}
