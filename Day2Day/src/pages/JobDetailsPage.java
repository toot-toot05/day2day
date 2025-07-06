// File: `src/pages/JobDetailsPage.java`
package pages;

import javax.swing.*;
import java.awt.*;

public class JobDetailsPage extends BasePage {
    private String postedUserFullName;
    public JButton backButton;
    public JButton viewProfileButton;

    public JobDetailsPage(String postedUserFullName) {
        super("Job Details");
        this.postedUserFullName = postedUserFullName;
    }

    @Override
    public void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Job Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        addComponent(titleLabel, 0, 0, 2, 1, gbc);

        JLabel jobTitleLabel = new JLabel("Job Title: Software Developer");
        addComponent(jobTitleLabel, 0, 1, 2, 1, gbc);

        JLabel descriptionLabel = new JLabel("Description:");
        addComponent(descriptionLabel, 0, 2, 2, 1, gbc);

        JTextArea descriptionArea = new JTextArea(5,20);
        descriptionArea.setEditable(false);
        descriptionArea.setText("Develop and maintain software applications...");
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        addComponent(scrollPane, 0, 3, 2, 1, gbc);

        JLabel requirementsLabel = new JLabel("Requirements: Java, Spring Boot");
        addComponent(requirementsLabel, 0, 4, 2, 1, gbc);

        viewProfileButton = new JButton("Posted By: " + postedUserFullName);
        addComponent(viewProfileButton, 0, 5, 2, 1, gbc);

        JButton applyButton = new JButton("Apply");
        addComponent(applyButton, 0, 6, 1, 1, gbc);

        backButton = new JButton("Back to Find Jobs");
        addComponent(backButton, 1, 6, 1, 1, gbc);
    }
}
