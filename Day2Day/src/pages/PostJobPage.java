// Java
package pages;

import javax.swing.*;
import java.awt.*;

public class PostJobPage extends BasePage {
    public JButton backButton;
    public JButton postButton;
    private JTextField jobTitleField;
    private JTextArea jobDescriptionArea;
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> jobTypeComboBox;
    private boolean initialized = false; // flag to avoid reinitialization

    public PostJobPage() {
        super("Post a Job");
    }

    @Override
    public void initializeComponents() {
        if (initialized) {
            return;
        }
        // Instead of clearing the content pane we just build the UI once.
        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Post a Job");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        addComponent(titleLabel, 0, 0, 2, 1, gbc);

        JLabel jobTitleLabel = new JLabel("Job Title:");
        addComponent(jobTitleLabel, 0, 1, 1, 1, gbc);
        jobTitleField = new JTextField(20);
        addComponent(jobTitleField, 1, 1, 1, 1, gbc);

        JLabel jobDescriptionLabel = new JLabel("Job Description:");
        addComponent(jobDescriptionLabel, 0, 2, 1, 1, gbc);
        jobDescriptionArea = new JTextArea(5, 20);
        jobDescriptionArea.setLineWrap(true);
        jobDescriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(jobDescriptionArea);
        addComponent(scrollPane, 1, 2, 1, 1, gbc);

        JLabel categoryLabel = new JLabel("Category:");
        addComponent(categoryLabel, 0, 3, 1, 1, gbc);
        String[] categories = { "IT", "Household", "Career" };
        categoryComboBox = new JComboBox<>(categories);
        addComponent(categoryComboBox, 1, 3, 1, 1, gbc);

        JLabel jobTypeLabel = new JLabel("Job Type:");
        addComponent(jobTypeLabel, 0, 4, 1, 1, gbc);
        String[] jobTypes = { "Day-to-Day", "Career" };
        jobTypeComboBox = new JComboBox<>(jobTypes);
        addComponent(jobTypeComboBox, 1, 4, 1, 1, gbc);

        postButton = new JButton("Post Job");
        postButton.setBackground(new Color(0, 123, 255));
        postButton.setForeground(Color.WHITE);
        addComponent(postButton, 0, 5, 1, 1, gbc);

        backButton = new JButton("Back to Dashboard");
        backButton.setBackground(new Color(108, 117, 125));
        backButton.setForeground(Color.WHITE);
        addComponent(backButton, 1, 5, 1, 1, gbc);

        initialized = true;
        frame.revalidate();
        frame.repaint();
    }

    public String getJobTitle() {
        return jobTitleField.getText().trim();
    }

    public String getJobDescription() {
        return jobDescriptionArea.getText().trim();
    }

    public String getCategory() {
        return (String) categoryComboBox.getSelectedItem();
    }

    public String getJobType() {
        return (String) jobTypeComboBox.getSelectedItem();
    }

    public JButton getPostButton() {
        return postButton;
    }
}
