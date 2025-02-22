package org.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BritInsurancePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By searchIcon = By.xpath("//button[@aria-label='Search button']/span");
    private By searchInput = By.xpath("//div[@class='header--search']/input");
    private By searchResults = By.cssSelector(".header--search__results .result");
    private By cookieAccept = By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowallSelection");

    public BritInsurancePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void navigateTo() {
        driver.get("https://www.britinsurance.com/");
    }

    public void acceptCookiesIfPresent() {
        List<WebElement> cookieButton = driver.findElements(cookieAccept);
        if (!cookieButton.isEmpty()) {
            cookieButton.get(0).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("CybotCookiebotDialogContentWrapper")));
        }
    }

    public void searchFor(String term) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-experience='desktop']")));
        wait.until(ExpectedConditions.elementToBeClickable(searchIcon)).click();
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.sendKeys(term);
    }

    public List<WebElement> getSearchResults() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResults));
    }
}