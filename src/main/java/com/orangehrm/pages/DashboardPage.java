package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private final By headerBreadcrumb = By.cssSelector(".oxd-topbar-header-breadcrumb h6");
    private final By pimMenuLink = By.xpath("//aside//a[contains(@href,'viewPimModule')]");
    private final By userDropdown = By.cssSelector(".oxd-userdropdown-tab");
    private final By logoutLink = By.xpath("//a[contains(@href,'/auth/logout')]");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void goToPIM() {
        click(pimMenuLink);
        waitForUrlContains("/pim/");
    }

    public void logout() {
        click(userDropdown);
        click(logoutLink);
        waitForUrlContains("/auth/login");
    }

    public boolean isOnDashboard() {
        try {
            waitForUrlContains("/dashboard/index");
        } catch (TimeoutException e) {
            return false;
        }
        return "Dashboard".equals(getHeaderText());
    }

    public String getHeaderText() {
        return getText(headerBreadcrumb);
    }
}
