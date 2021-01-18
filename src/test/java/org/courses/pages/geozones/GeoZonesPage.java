package org.courses.pages.geozones;

import org.courses.pages.common.CommonTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GeoZonesPage {

    private String geoZonesPageUrl = "http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones";
    private WebDriver driverHere;


    @FindBy(css = "h1")
    private WebElement pageHeader;

    public GeoZonesPage(WebDriver driver) {
        driverHere = driver;
        PageFactory.initElements(driverHere, this);
    }

    public void open() {
        driverHere.navigate().to(geoZonesPageUrl);
        driverHere.manage().window().maximize();
    }

    public CommonTable getCountriesTable() {
        return new CommonTable(driverHere, By.cssSelector("table.dataTable"),
                By.cssSelector("tr.row"),
                By.cssSelector("td"));
    }
}

