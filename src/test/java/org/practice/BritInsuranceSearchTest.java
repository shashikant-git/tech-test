package org.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class BritInsuranceSearchTest {
    private static final Logger logger = LoggerFactory.getLogger(BritInsuranceSearchTest.class);
    private WebDriver driver;
    private BritInsurancePage page;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        page = new BritInsurancePage(driver);
        logger.info("Initialized WebDriver and BritInsurancePage");
    }

    @Test
    public void testSearchIFRS17() {
        logger.info("Starting UI test for IFRS 17 search");
        page.navigateTo();
        logger.info("Navigated to britinsurance.com");
        page.acceptCookiesIfPresent();
        logger.info("Handled cookie popup if present");
        page.searchFor("IFRS 17");
        logger.info("Performed search for 'IFRS 17'");

        List<WebElement> searchResults = page.getSearchResults();
        logger.info("Found {} search results", searchResults.size());

        Assert.assertEquals(searchResults.size(), 5, "Expected 5 search results");
        String expectedTitle = "Interim results for the six months ended 30 June 2022";
        boolean titleFound = false;
        for (WebElement result : searchResults) {
            String resultText = result.getText().trim();
            if (resultText.equalsIgnoreCase(expectedTitle)) {
                titleFound = true;
                break;
            }
        }
        Assert.assertTrue(titleFound, "Expected title not found: " + expectedTitle);
        logger.info("Verified 5 results and presence of expected title");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Closed WebDriver");
        }
    }
}