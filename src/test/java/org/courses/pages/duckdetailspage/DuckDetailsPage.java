package org.courses.pages.duckdetailspage;

import org.courses.pages.duckdetailspage.components.DuckDetailsSection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DuckDetailsPage {
    private WebDriver driverHere;

    @FindBy(css = "h1.title")
    private WebElement pageHeader;

    private By cartQuantityBy = By.cssSelector("#cart .quantity");
    private String quantityTamplateXpath = "//*[@id='cart']//*[@class='quantity'][.='%s']";


    public DuckDetailsPage(WebDriver mePersonalDriver) {
        this.driverHere = mePersonalDriver;
        PageFactory.initElements(driverHere, this);
    }

    public String getPageName() {
        return pageHeader.getText();
    }

    public String getDuckName() {
        return getPageName();
    }

    public DuckDetailsSection getDuckDetailsSection() {
        return new DuckDetailsSection(driverHere);
    }

    public By getCartQuantityBy() {
        return cartQuantityBy;
    }

    public String getQuantityTamplateXpath() {
        return quantityTamplateXpath;
    }

    public WebElement getCartCounter() {
        return driverHere.findElement(cartQuantityBy);
    }
}
