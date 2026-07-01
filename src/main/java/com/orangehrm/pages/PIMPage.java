package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PIMPage extends BasePage {

    private final By employeeNameInput = By.xpath("(//input[@placeholder='Type for hints...'])[1]");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By noRecordsMessage = By.xpath("//span[normalize-space()='No Records Found']");
    private final By resultRow = By.cssSelector(".oxd-table-body .oxd-table-row");
    private final By loadingSpinner = By.cssSelector(".oxd-loading-spinner");

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    public void searchEmployeeByName(String name) {
        type(employeeNameInput, name);
        click(searchButton);
        waitForElementToDisappear(loadingSpinner);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(resultRow),
                ExpectedConditions.visibilityOfElementLocated(noRecordsMessage)));
    }

    public boolean hasResults() {
        return !driver.findElements(resultRow).isEmpty();
    }

    public boolean isNoRecordsDisplayed() {
        return !driver.findElements(noRecordsMessage).isEmpty();
    }

    public boolean isOnPIMPage() {
        return driver.getCurrentUrl().contains("/pim/")
                && isElementVisible(searchButton);
    }
}
