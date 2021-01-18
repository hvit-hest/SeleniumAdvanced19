package org.courses.pages.editcountrypage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditCountryPage {

    private WebDriver driverHere;
    private String iconsLinksXpath = "//*[@id='content']//td/a[@href][./i]";

    @FindBy(css = "h1")
    private WebElement pageHeader;

    public EditCountryPage(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
    }

    public void open() {
        driverHere.manage().window().maximize();
    }

    public List<WebElement> getHrefList() {
        return driverHere.findElements(By.xpath(iconsLinksXpath));
    }
}
