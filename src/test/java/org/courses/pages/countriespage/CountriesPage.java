package org.courses.pages.countriespage;

import org.courses.pages.common.CommonTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CountriesPage {

    private String countriesPageUrl = "http://localhost/litecart/admin/?app=countries&doc=countries";
    private WebDriver driverHere;


    @FindBy(css = "h1")
    private WebElement pageHeader;

    String countryByNameTemplateXpath = "//tr/td/a[.='%s']";



    public CountriesPage(WebDriver driver) {
        driverHere = driver;
        PageFactory.initElements(driverHere, this);
    }

    public void open() {
        driverHere.navigate().to(countriesPageUrl);
        driverHere.manage().window().maximize();
    }

    public CommonTable getCountriesTable() {
        return new CommonTable(driverHere, By.cssSelector("table.dataTable"),
                By.cssSelector("tr.row"),
                By.cssSelector("td"));
    }

    public WebElement getCountryByName(String countryName) {
        return driverHere.findElement(By.xpath(String.format(countryByNameTemplateXpath, countryName)));
    }
}

