// Java
package pages;

import functionality.DataAccess;
import javax.swing.*;
import java.awt.*;

public class DashboardPage extends BasePage {
    public JButton postJobButton;
    public JButton dayToDayJobsButton;
    public JButton careerJobsButton;
    public JButton logoutButton;

    public DashboardPage() {
        super("Dashboard");
    }

    @Override
    public void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Use the logged-in user's full name for a personalized welcome.
        JLabel welcomeLabel = new JLabel("Welcome " + DataAccess.currentUserFullName + " to Day2Day Dashboard!");
        addComponent(welcomeLabel, 0, 0, 2, 1, gbc);

        postJobButton = new JButton("Post a Job");
        addComponent(postJobButton, 0, 1, 1, 1, gbc);

        dayToDayJobsButton = new JButton("Day-to-Day Jobs");
        addComponent(dayToDayJobsButton, 1, 1, 1, 1, gbc);

        careerJobsButton = new JButton("Career Jobs");
        addComponent(careerJobsButton, 0, 2, 1, 1, gbc);

        logoutButton = new JButton("Logout");
        addComponent(logoutButton, 1, 2, 1, 1, gbc);
    }
}
