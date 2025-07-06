// Java
package functionality;

import pages.BasePage;
import pages.LoginPage;
import pages.DashboardPage;
import pages.SignupPage;
import pages.CareerJobsPage;
import pages.PostJobPage;
import pages.DayToDayJobsPage;

public class PageManager implements PageNavigator {
    private BasePage currentPage;

    @Override
    public void navigateTo(BasePage page) {
        if (currentPage != null && currentPage.frame != null) {
            currentPage.frame.dispose(); // Properly dispose of the current page's frame
        }
        currentPage = page;
        currentPage.showPage(); // Show the new page
    }

    public LoginPage getLoginPage() {
        return new LoginPage();
    }

    public DashboardPage getDashboardPage() {
        return new DashboardPage();
    }

    public SignupPage getSignupPage() {
        return new SignupPage();
    }

    public CareerJobsPage getCareerJobsPage() {
        return new CareerJobsPage();
    }

    public PostJobPage getPostJobPage() {
        return new PostJobPage();
    }

    public DayToDayJobsPage getDayToDayJobsPage() {
        return new DayToDayJobsPage();
    }
}
