package pages;

import functionality.DataAccess;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DayToDayJobsPage extends BasePage {
    public JButton backButton;
    private JPanel jobsContainer;
    private Timer refreshTimer;

    public DayToDayJobsPage() {
        super("Day-to-Day Jobs");
    }

    @Override
    public void initializeComponents() {
        jobsContainer = new JPanel();
        jobsContainer.setLayout(new BoxLayout(jobsContainer, BoxLayout.Y_AXIS));
        jobsContainer.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Day-to-Day Jobs");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addComponent(titleLabel, 0, 0, 2, 1, gbc);

        JScrollPane scrollPane = new JScrollPane(jobsContainer);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        addComponent(scrollPane, 0, 1, 2, 1, gbc);

        backButton = new JButton("Back to Dashboard");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(0, 123, 255));
        backButton.setForeground(Color.WHITE);
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        addComponent(backButton, 0, 2, 2, 1, gbc);

        // Load jobs initially
        loadJobs();

        // Set up a timer to refresh jobs every 5 seconds
        refreshTimer = new Timer(5000, e -> loadJobs());
        refreshTimer.start();
    }

    private void loadJobs() {
        jobsContainer.removeAll();
        List<String[]> jobs = DataAccess.getDayToDayJobsWithDetails();
        for (String[] job : jobs) {
            String jobId = job[0];
            String jobDetails = "Job Title: " + job[1] + "\nDescription: " + job[2] + "\nCategory: " + job[3] + "\nPosted By: " + job[4];

            RoundedPanel jobCard = new RoundedPanel();
            jobCard.setBackground(new Color(245, 245, 245));
            jobCard.setLayout(new BorderLayout());
            jobCard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JTextArea jobTextArea = new JTextArea(jobDetails);
            jobTextArea.setEditable(false);
            jobTextArea.setLineWrap(true);
            jobTextArea.setWrapStyleWord(true);
            jobTextArea.setOpaque(false);
            jobTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
            jobCard.add(jobTextArea, BorderLayout.CENTER);

            if (job[4].equals(DataAccess.currentUserFullName)) {
                JButton deleteButton = new JButton("Delete");
                deleteButton.setBackground(new Color(220, 53, 69));
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
