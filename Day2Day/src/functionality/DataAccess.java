// Java
package functionality;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {
    public static int currentUserId = -1;
    public static String currentUserFullName = "";

    public static String validateUser(String username, String password) {
        String fullName = null;
        String sql = "SELECT id, full_name FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                currentUserId = rs.getInt("id");
                fullName = rs.getString("full_name");
                currentUserFullName = fullName;
                System.out.println("Login success. currentUserId set to: " + currentUserId);
            } else {
                System.out.println("Login failed. No matching user.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullName;
    }

    public static boolean insertUser(String username, String password, String fullName) {
        String sql = "INSERT INTO users(username, password, full_name) VALUES(?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, fullName);
            pstmt.executeUpdate();
            System.out.println("User inserted: " + username);
            return true;
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed: users.username")) {
                System.out.println("Error: Username already exists.");
            } else {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean insertJob(String title, String description, String category, String posterName, String jobOrigin) {
        String sql = "INSERT INTO jobs(title, description, category, posted_by, job_origin) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setString(3, category);
            pstmt.setString(4, posterName);
            pstmt.setString(5, jobOrigin);
            pstmt.executeUpdate();
            System.out.println("Job inserted: " + title);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<String> getJobsByCategory(String category) {
        List<String> jobs = new ArrayList<>();
        String sql;
        if (category.equalsIgnoreCase("All")) {
            sql = "SELECT title, description, category, posted_by FROM jobs";
        } else {
            sql = "SELECT title, description, category, posted_by FROM jobs WHERE category = ?";
        }
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (!category.equalsIgnoreCase("All")) {
                pstmt.setString(1, category);
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String job = "Job Title: " + rs.getString("title") + "\n" +
                        "Description: " + rs.getString("description") + "\n" +
                        "Category: " + rs.getString("category") + "\n" +
                        "Posted By: " + rs.getString("posted_by") + "\n";
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    public static List<String> getCareerJobs() {
        List<String> jobs = new ArrayList<>();
        String sql = "SELECT title, description, category, posted_by FROM jobs WHERE job_origin = 'career'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String job = "Job Title: " + rs.getString("title") + "\n" +
                        "Description: " + rs.getString("description") + "\n" +
                        "Category: " + rs.getString("category") + "\n" +
                        "Posted By: " + rs.getString("posted_by") + "\n";
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    // Added method to get day-to-day jobs
    public static List<String> getDayToDayJobs() {
        List<String> jobs = new ArrayList<>();
        String sql = "SELECT title, description, category, posted_by FROM jobs WHERE job_origin = 'day-to-day'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String job = "Job Title: " + rs.getString("title") + "\n" +
                        "Description: " + rs.getString("description") + "\n" +
                        "Category: " + rs.getString("category") + "\n" +
                        "Posted By: " + rs.getString("posted_by") + "\n";
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }
    
    
    public static List<String[]> getCareerJobsWithDetails() {
        List<String[]> jobs = new ArrayList<>();
        String sql = "SELECT id, title, description, category, posted_by FROM jobs WHERE job_origin = 'career'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                jobs.add(new String[]{
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getString("posted_by")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    public static List<String[]> getDayToDayJobsWithDetails() {
        List<String[]> jobs = new ArrayList<>();
        String sql = "SELECT id, title, description, category, posted_by FROM jobs WHERE job_origin = 'day-to-day'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                jobs.add(new String[]{
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getString("posted_by")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    public static void deleteJobById(String jobId) {
        String sql = "DELETE FROM jobs WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, jobId);
            pstmt.executeUpdate();
            System.out.println("Job deleted with ID: " + jobId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
