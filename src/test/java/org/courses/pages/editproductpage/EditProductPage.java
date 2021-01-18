package org.courses.pages.editproductpage;

import org.courses.pages.addnewproductpage.GeneralSection;
import org.courses.pages.addnewproductpage.InformationSection;
import org.courses.pages.addnewproductpage.PricesSection;
import org.courses.pages.addnewproductpage.components.NewProductTabs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditProductPage {


    private WebDriver driverHere;
    private By buttonSaveBy = By.cssSelector("button[name='save']");
    private By buttonCancelBy = By.cssSelector("button[name='cancel']");
    private By buttonDeleteBy = By.cssSelector("button[name='delete']");


    @FindBy(css = "h1.title")
    private WebElement pageHeader;


    public EditProductPage(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
        PageFactory.initElements(driverHere, this);
    }

    public void open() {
        driverHere.manage().window().maximize();
    }

    public String getPageHeader() {
        return driverHere.findElement(By.cssSelector("h1")).getText();
    }
}
