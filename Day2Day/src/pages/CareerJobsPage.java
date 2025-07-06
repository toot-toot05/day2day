package pages;

import functionality.DataAccess;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CareerJobsPage extends BasePage {
    public JButton backButton;
    private JPanel jobsContainer;
    private Timer refreshTimer;

    public CareerJobsPage() {
        super("Career Jobs - DAY 2 DAY");
    }

    @Override
    public void initializeComponents() {
        jobsContainer = new JPanel();
        jobsContainer.setLayout(new BoxLayout(jobsContainer, BoxLayout.Y_AXIS));
        jobsContainer.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Career Jobs");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE); // White text for contrast
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addComponent(titleLabel, 0, 0, 2, 1, gbc);

        JScrollPane scrollPane = new JScrollPane(jobsContainer);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(new Color(45, 45, 45)); // Match background
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        addComponent(scrollPane, 0, 1, 2, 1, gbc);

        backButton = new JButton("Back to Dashboard");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(70, 130, 180)); // Modern blue
        backButton.setForeground(Color.WHITE);
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        addComponent(backButton, 0, 2, 2, 1, gbc);

        loadJobs();

        refreshTimer = new Timer(5000, e -> loadJobs());
        refreshTimer.start();
    }

    private void loadJobs() {
        jobsContainer.removeAll();
        List<String[]> jobs = DataAccess.getCareerJobsWithDetails();
        for (String[] job : jobs) {
            String jobId = job[0];
            String jobDetails = "<html><b>Job Title:</b> " + job[1] + "<br><b>Description:</b> " + job[2] +
                    "<br><b>Category:</b> " + job[3] + "<br><b>Posted By:</b> " + job[4] + "</html>";

            RoundedPanel jobCard = new RoundedPanel(15, new Color(60, 60, 60)); // Darker card
            jobCard.setLayout(new BorderLayout());
            jobCard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel jobTextLabel = new JLabel(jobDetails);
            jobTextLabel.setForeground(Color.WHITE); // White text
            jobTextLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            jobCard.add(jobTextLabel, BorderLayout.CENTER);

            if (job[4].equals(DataAccess.currentUserFullName)) {
                JButton deleteButton = new JButton("Delete");
                deleteButton.setBackground(new Color(220, 53, 69)); // Red for delete
                deleteButton.setForeground(Color.WHITE);
                deleteButton.addActionListener(e -> {
                    DataAccess.deleteJobById(jobId);
                    loadJobs();
                });
                jobCard.add(deleteButton, BorderLayout.EAST);
            }

            jobsContainer.add(jobCard);
            jobsContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        jobsContainer.revalidate();
        jobsContainer.repaint();
    }

    @Override
    public void showPage() {
        super.showPage();
        if (refreshTimer != null) {
            refreshTimer.start();
        }
    }

    public void disposePage() {
        if (refreshTimer != null) {
            refreshTimer.stop();
        }
        frame.dispose();
    }
}
