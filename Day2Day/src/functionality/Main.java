// Java
package functionality;

import pages.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    // method for secure password check.
    public static boolean isValidPassword(String password) {
        if (password.length() < 6) return false;
        boolean hasLetter = password.matches(".*[A-Za-z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[^A-Za-z0-9].*");
        return hasLetter && hasDigit && hasSpecial;
    }

    // method for secure username check.
    public static boolean isValidUsername(String username) {
        // Username must contain at least one number and only underscores for spacing.
        return username.matches("^(?=.*\\d)[A-Za-z0-9_]+$");
    }

    public static void main(String[] args) {
        PageManager pageManager = new PageManager();

        // Retrieve pages.
        LoginPage loginPage = pageManager.getLoginPage();
        DashboardPage dashboardPage = pageManager.getDashboardPage();
        SignupPage signupPage = pageManager.getSignupPage();
        PostJobPage postJobPage = pageManager.getPostJobPage();

        // Initialize pages once for static pages.
        loginPage.initializeComponents();
        signupPage.initializeComponents();
        dashboardPage.initializeComponents();
        postJobPage.initializeComponents();

        // Login page actions.
        loginPage.loginButton.addActionListener(e -> {
            String username = loginPage.getUsername();
            String password = loginPage.getPassword();
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in both username and password.");
                return;
            }
            String fullName = DataAccess.validateUser(username, password);
            if (fullName != null) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                pageManager.navigateTo(dashboardPage);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials.");
            }
        });

        loginPage.signupButton.addActionListener(e -> pageManager.navigateTo(signupPage));

        // Signup page actions.
        signupPage.backButton.addActionListener(e -> pageManager.navigateTo(loginPage));
        signupPage.signupButton.addActionListener(e -> {
            String fullName = signupPage.getFullName();
            String username = signupPage.getUsername();
            String password = signupPage.getPassword();
            String confirmPassword = signupPage.getConfirmPassword();
            if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }
            if (!isValidUsername(username)) {
                JOptionPane.showMessageDialog(null, "Username must contain at least one number and only underscores for spacing.");
                return;
            }
            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(null, "Password must be at least 6 characters, "
                        + "contain a letter, a digit, and a special character.");
                return;
            }
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match.");
            } else {
                boolean inserted = DataAccess.insertUser(username, password, fullName);
                if (inserted) {
                    JOptionPane.showMessageDialog(null, "Signup successful! You can now log in.");
                    pageManager.navigateTo(loginPage);
                } else {
                    JOptionPane.showMessageDialog(null, "Error during signup. Try again.");
                }
            }
        });

        // Dashboard page actions.
        dashboardPage.logoutButton.addActionListener(e -> {
            DataAccess.currentUserId = -1;
            DataAccess.currentUserFullName = "";
            pageManager.navigateTo(loginPage);
        });
        dashboardPage.postJobButton.addActionListener(e -> pageManager.navigateTo(postJobPage));
        dashboardPage.dayToDayJobsButton.addActionListener(e -> {
            DayToDayJobsPage newDayToDayJobsPage = pageManager.getDayToDayJobsPage();
            newDayToDayJobsPage.initializeComponents();
            // Set back button action to navigate to the dashboard.
            newDayToDayJobsPage.backButton.addActionListener(ev -> pageManager.navigateTo(dashboardPage));
            pageManager.navigateTo(newDayToDayJobsPage);
        });
        dashboardPage.careerJobsButton.addActionListener(e -> {
            CareerJobsPage newCareerJobsPage = pageManager.getCareerJobsPage();
            newCareerJobsPage.initializeComponents();
            // Set back button action to navigate to the dashboard.
            newCareerJobsPage.backButton.addActionListener(ev -> pageManager.navigateTo(dashboardPage));
            pageManager.navigateTo(newCareerJobsPage);
        });

        // Post job page actions.
        postJobPage.getPostButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = postJobPage.getJobTitle();
                String description = postJobPage.getJobDescription();
                String category = postJobPage.getCategory();
                String jobType = postJobPage.getJobType().toLowerCase();
                String posterName = DataAccess.currentUserFullName;
                boolean success = DataAccess.insertJob(title, description, category, posterName, jobType);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Job posted successfully!");
                    if (jobType.equals("career")) {
                        CareerJobsPage newCareerJobsPage = pageManager.getCareerJobsPage();
                        newCareerJobsPage.initializeComponents();
                        newCareerJobsPage.backButton.addActionListener(ev -> pageManager.navigateTo(dashboardPage));
                        pageManager.navigateTo(newCareerJobsPage);
                    } else {
                        DayToDayJobsPage newDayToDayJobsPage = pageManager.getDayToDayJobsPage();
                        newDayToDayJobsPage.initializeComponents();
                        newDayToDayJobsPage.backButton.addActionListener(ev -> pageManager.navigateTo(dashboardPage));
                        pageManager.navigateTo(newDayToDayJobsPage);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error posting job.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        postJobPage.backButton.addActionListener(e -> pageManager.navigateTo(dashboardPage));

        // Start with the login page.
        pageManager.navigateTo(loginPage);
    }
}
