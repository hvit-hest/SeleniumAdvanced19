package org.courses.pages.securitysettingspage;

import org.courses.pages.common.CommonTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SecuritySettingsPage {
    private WebDriver driverHere;

    @FindBy(css = "h1.title")
    private WebElement pageHeader;


    public SecuritySettingsPage(WebDriver mePersonalDriver) {
        this.driverHere = mePersonalDriver;
        PageFactory.initElements(driverHere, this);
    }

    public void open() {
        driverHere.manage().window().maximize();
    }

    public String getPageName() {
        return pageHeader.getText();
    }

    public CommonTable getSettingsSecurityTable() {
        return new CommonTable(driverHere, By.cssSelector("table.dataTable"),
                By.xpath(".//tr[not(@class='header')]"),
                By.cssSelector("td"));

    }

    public void setCaptchaRadioButton(String trueOrFalse) {
        driverHere.findElement(By.xpath("//td[.//u[contains(.,'CAPTCHA')]]")).
                findElement(By.xpath(String.format(".//label[contains(.,'%s')]", trueOrFalse))).click();
    }

    public void clickButtonSave() {
        driverHere.findElement(By.cssSelector("button[name='save']")).click();
    }
}

