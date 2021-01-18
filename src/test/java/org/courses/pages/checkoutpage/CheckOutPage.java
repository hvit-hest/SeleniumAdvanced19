package org.courses.pages.checkoutpage;

import org.courses.pages.checkoutpage.components.CartFormSection;
import org.courses.pages.common.CommonTable;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CheckOutPage {

    private String checkOutPageUrl = "http://http://localhost/litecart/en/checkout";
    private WebDriver driverHere;

    @FindBy(css = "h1.title")
    private WebElement pageHeader;

    private By paymentDueBy = By.xpath("//tr[./*[.='Payment Due:']]");

    private By orderSummaryTableBy = By.cssSelector("table.dataTable");

    public CheckOutPage(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
        PageFactory.initElements(driverHere, this);
    }

     public void open() {
        driverHere.navigate().to(checkOutPageUrl);
        driverHere.manage().window().maximize();
    }

    public CartFormSection getCartFormSection() {
        return new CartFormSection(driverHere);
    }

    public WebElement getOrderSummaryTableWE() {
        return driverHere.findElement(orderSummaryTableBy);
    }

    public CommonTable getOrderSummaryTable() {
        return new CommonTable(driverHere, By.cssSelector("table.dataTable"), By.cssSelector("tr"), By.cssSelector("td"));
    }

    public void stopRunningLineOfDucks() {
        if (getCartFormSection().getShortCuts().size() > 0)
            try {
                getCartFormSection().getShortCuts().get(0).click();
            } catch (StaleElementReferenceException se) {
               getCartFormSection().getShortCuts().get(0).click();
            }
    }

    public WebElement getPageHeader() {
        return pageHeader;
    }

    public By getPaymentDueBy() {
        return paymentDueBy;
    }

    public By getOrderSummaryTableBy() {
        return orderSummaryTableBy;
    }
}
