package org.courses.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SauceLabTest extends BaseTest {

    private WebDriver myPersonalDriver;
    private final String w3schoolPage = "https://www.w3schools.com";
    private String titleToCheck = "w3schools online web tutorials";

    @BeforeClass
    public void beforeClass() {
        myPersonalDriver = getMyPersonalDriver();
    }

    @Test
    public void openPageTest() {

        //Open page
        myPersonalDriver.get(w3schoolPage);
        myPersonalDriver.manage().window().maximize();
        //Check title (Also it is possible to look for some elements on the page)
        Assert.assertTrue(myPersonalDriver.getTitle().toLowerCase().contains(titleToCheck),
                "the test does not pass");
    }


    @AfterClass
    public void afterClass() {
        ((JavascriptExecutor) myPersonalDriver).executeScript("sauce:job-result=" + ("passed"));
        myPersonalDriver.quit();
    }
}