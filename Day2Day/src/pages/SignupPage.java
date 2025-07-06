package pages;

import javax.swing.*;
import java.awt.*;

public class SignupPage extends BasePage {
    public JButton backButton;
    public JButton signupButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField fullNameField;

    public SignupPage() {
        super("Signup");
    }

    @Override
    public void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Signup");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        addComponent(titleLabel, 0, 0, 2, 1, gbc);

        JLabel fullNameLabel = new JLabel("Full Name:");
        addComponent(fullNameLabel, 0, 1, 1, 1, gbc);
        fullNameField = new JTextField(20);
        addComponent(fullNameField, 1, 1, 1, 1, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        addComponent(usernameLabel, 0, 2, 1, 1, gbc);
        usernameField = new JTextField(20);
        addComponent(usernameField, 1, 2, 1, 1, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        addComponent(passwordLabel, 0, 3, 1, 1, gbc);
        passwordField = new JPasswordField(20);
        addComponent(passwordField, 1, 3, 1, 1, gbc);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        addComponent(confirmPasswordLabel, 0, 4, 1, 1, gbc);
        confirmPasswordField = new JPasswordField(20);
        addComponent(confirmPasswordField, 1, 4, 1, 1, gbc);

        signupButton = new JButton("Signup");
        addComponent(signupButton, 0, 5, 1, 1, gbc);

        backButton = new JButton("Back to Login");
        addComponent(backButton, 1, 5, 1, 1, gbc);
    }

    public String getFullName() {
        return fullNameField.getText().trim();
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword()).trim();
    }

    public String getConfirmPassword() {
        return new String(confirmPasswordField.getPassword()).trim();
    }
}
