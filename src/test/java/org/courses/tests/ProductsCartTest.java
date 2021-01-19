package org.courses.tests;

import org.courses.pages.checkoutpage.CheckOutPage;
import org.courses.pages.duckdetailspage.DuckDetailsPage;
import org.courses.pages.mainpage.MainPage;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

public class ProductsCartTest extends BaseTest {

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

        mainPage = new MainPage(myPersonalDriver);
        mainPage.open();
        int[] l = {0};
        //add first three ducks
        for (int i = 0; i < 3; i++) {
            mainPage.getAllDuckProducts().get(i).click();
            DuckDetailsPage duckDetailsPage = new DuckDetailsPage(myPersonalDriver);
            String cardCounter = duckDetailsPage.getCartCounterWE().getText();
            duckDetailsPage.getDuckDetailsSection().addDuckToCart(1);
            //Check cart's counter
            wait.until(ExpectedConditions.invisibilityOfElementWithText(duckDetailsPage.getCartQuantityBy(), cardCounter));
            Assert.assertEquals(duckDetailsPage.getCartCounterWE().getText(), Integer.toString(Integer.parseInt(cardCounter) + 1));
            mainPage = new MainPage(myPersonalDriver);
            mainPage.open();
        }
        mainPage.clickCheckOutLink();
        CheckOutPage checkOutPage = new CheckOutPage(myPersonalDriver);


        //1. stop "running line" of ducks' shortcuts
        if (checkOutPage.getCartFormSection().getShortCuts().size() > 0)
            try {
                checkOutPage.getCartFormSection().getShortCuts().get(0).click();
            } catch (StaleElementReferenceException se) {
                checkOutPage.getCartFormSection().getShortCuts().get(0).click();
            }

        //Remove ducks from cart one by one waiting table renew and old "remove" button stale
        //press enabled "remove" button if exist, it has to
        while (myPersonalDriver.findElements(checkOutPage.getCartFormSection().getRemoveCartButtonBy()).
                stream().filter(WebElement::isEnabled).collect(Collectors.toList()).size() != 0) {
            //the button will be stale
            WebElement oldButton = myPersonalDriver.findElements(checkOutPage.getCartFormSection().getRemoveCartButtonBy()).
                    stream().filter(WebElement::isEnabled).collect(Collectors.toList()).get(0);
            //the table will be stale
            WebElement oldPaymentDueTable = myPersonalDriver.findElement(checkOutPage.getOrderSummaryTableBy());
            //CLICK!!!
            myPersonalDriver.findElements(checkOutPage.getCartFormSection().getRemoveCartButtonBy()).stream().
                    filter(WebElement::isEnabled).collect(Collectors.toList()).get(0).click();
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
