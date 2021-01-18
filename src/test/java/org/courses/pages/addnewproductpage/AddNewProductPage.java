package org.courses.pages.addnewproductpage;

import org.courses.pages.addnewproductpage.components.NewProductTabs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewProductPage {


    private WebDriver driverHere;
    private By buttonSaveBy = By.cssSelector("button[name='save']");
    private By buttonCancelBy = By.cssSelector("button[name='cancel']");
    private By buttonDeleteBy = By.cssSelector("button[name='delete']");


    @FindBy(css = "h1.title")
    private WebElement pageHeader;


    public AddNewProductPage(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
        PageFactory.initElements(driverHere, this);
    }

    public void open() {
        driverHere.manage().window().maximize();
    }

    public void openTab(NewProductTabs newProductTabs) {
        driverHere.findElement(newProductTabs.getTabBy()).click();
    }

    public GeneralSection getGeneralSection() {
        return new GeneralSection(driverHere);
    }

    public InformationSection getInformationSection() {
        return new InformationSection(driverHere);
    }

    public PricesSection getPricesSection() {
        return new PricesSection(driverHere);
    }

    public void clickButtonSave() {
        driverHere.findElement(buttonSaveBy).click();
    }

    public void clickButtonCancel() {
        driverHere.findElement(buttonCancelBy).click();
    }

    public void clickButtonDelete() {
        driverHere.findElement(buttonDeleteBy).click();
    }
}
