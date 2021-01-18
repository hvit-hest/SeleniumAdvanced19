package org.courses.tests;

import org.courses.pages.checkoutpage.CheckOutPage;
import org.courses.pages.duckdetailspage.DuckDetailsPage;
import org.courses.pages.mainpage.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        WebDriverWait wait = new WebDriverWait(myPersonalDriver, 4);
        int[] l = {0};
        //add first three ducks
        for (int i = 0; i < 3; i++) {
            mainPage.getAllDuckProducts().get(i).click();
            DuckDetailsPage duckDetailsPage = new DuckDetailsPage(myPersonalDriver);
            String cardCounter = duckDetailsPage.getCartCounter().getText();
            duckDetailsPage.getDuckDetailsSection().addDuckToCart(1);
            //Check cart's counter
            wait.until(ExpectedConditions.invisibilityOfElementWithText(duckDetailsPage.getCartQuantityBy(), cardCounter));
            Assert.assertEquals(duckDetailsPage.getCartCounter().getText(), Integer.toString(Integer.parseInt(cardCounter) + 1));
            mainPage = new MainPage(myPersonalDriver);
            mainPage.open();
        }

        mainPage.clickCheckOutLink();
        CheckOutPage checkOutPage = new CheckOutPage(myPersonalDriver);
        //1. stop "running line" of ducks' shortcuts
        checkOutPage.stopRunningLineOfDucks();

        //Remove ducks from cart one by one waiting table renew and old "remove" button stale
        //press enabled "remove" button if exist, it has to
        while (checkOutPage.getCartFormSection().getRemoveCardButtonsEnabled().size() != 0) {
            //the button will be stale
            WebElement oldButton = checkOutPage.getCartFormSection().getRemoveCardButtonsEnabled().get(0);
            //the table will be stale
            WebElement oldPaymentDueTable = checkOutPage.getOrderSummaryTableWE();
            //CLICK!!!
            oldButton.click();
            //wait until stale button disappears, it has to
            wait.until(ExpectedConditions.stalenessOf(oldButton));
            //wait until stale table disappears, it has to
            wait.until(ExpectedConditions.stalenessOf(oldPaymentDueTable));
        }
    }


    @AfterClass
    public void afterClass() {
        myPersonalDriver.quit();
    }

}
