// Java
package pages;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends BasePage {
    public JButton loginButton;
    public JButton signupButton;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {
        super("Login");
    }

    @Override
    public void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        addComponent(titleLabel, 0, 0, 2, 1, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        addComponent(usernameLabel, 0, 1, 1, 1, gbc);
        usernameField = new JTextField(20);
        addComponent(usernameField, 1, 1, 1, 1, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        addComponent(passwordLabel, 0, 2, 1, 1, gbc);
        passwordField = new JPasswordField(20);
        addComponent(passwordField, 1, 2, 1, 1, gbc);

        loginButton = new JButton("Login");
        addComponent(loginButton, 0, 3, 1, 1, gbc);

        signupButton = new JButton("Signup");
        addComponent(signupButton, 1, 3, 1, 1, gbc);
        
        // Set default button so enter key triggers login
        frame.getRootPane().setDefaultButton(loginButton);
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword()).trim();
    }
}
