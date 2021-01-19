package org.courses.tests;

import org.courses.pages.checkoutpage.CheckOutPage;
import org.courses.pages.duckdetailspage.DuckDetailsPage;
import org.courses.pages.mainpage.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PageObjectCartTest extends BaseTest {

    private WebDriver myPersonalDriver;
    private String userEmail;
    private String userPassword;
    private MainPage mainPage;

    @BeforeClass
    public void beforeClass() {
        userPassword = getUserPassword();
        userEmail = getUserEmail();
        myPersonalDriver = getMyPersonalDriver();
        mainPage = new MainPage(myPersonalDriver);
        mainPage.open();
        mainPage.login(userEmail, userPassword);
    }

    @Test
    public void productsCartTest() {

        SoftAssert softAssert = new SoftAssert();
        //add first three ducks to cart
        for (int i = 0; i < 3; i++) {
            mainPage.openNthDuckDetails(i);
            DuckDetailsPage duckDetailsPage = new DuckDetailsPage(myPersonalDriver);
            String ducksInCartCounter = duckDetailsPage.getCartCounter();
            duckDetailsPage.getDuckDetailsSection().addDuckToCart(1);
            //Check cart's counter changed
            duckDetailsPage.waitSecondsTillDuckCounterChange(ducksInCartCounter, 4);
            softAssert.assertEquals(duckDetailsPage.getCartCounter(), Integer.toString(Integer.parseInt(ducksInCartCounter) + 1));
            mainPage = new MainPage(myPersonalDriver);
            mainPage.open();
        }

        mainPage.clickCheckOutLink();
        CheckOutPage checkOutPage = new CheckOutPage(myPersonalDriver);
        //1. stop "running line" of ducks' shortcuts
        checkOutPage.stopRunningLineOfDucks();
        //2. remove all ducks
        checkOutPage.removeAllDucks();
        //3. check message that all ducks removed
        softAssert.assertTrue(checkOutPage.thereIsNoMoreDucksInCartMessage());
        softAssert.assertAll();
    }

    @AfterClass
    public void afterClass() {
        myPersonalDriver.quit();
    }
}
