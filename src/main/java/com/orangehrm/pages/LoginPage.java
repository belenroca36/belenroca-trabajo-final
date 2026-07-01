package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final String LOGIN_URL =
            "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By errorMessage = By.cssSelector(".oxd-alert-content-text");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        driver.get(LOGIN_URL);
        waitForUrlContains("/auth/login");
    }

    public void enterUserName(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void loginAs(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        click(loginButton);
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("/auth/login")
                && isElementVisible(usernameInput);
    }

    public boolean isErrorDisplayed() {
        return isElementVisible(errorMessage);
    }
}
