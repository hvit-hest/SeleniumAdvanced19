package org.courses.pages.catalogpage;

import org.courses.pages.common.CommonTable;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CatalogPage {

    private String catalogPageUrl = "http://localhost/litecart/admin/?app=catalog&doc=catalog";
    private WebDriver driverHere;

    @FindBy(css = "h1.title")
    private WebElement pageHeader;

    @FindBy(xpath = "//a[@class='button'][contains(.,'Add New Product')]")
    private WebElement addNewProductButton;

    @FindBy(xpath = "//*[@class='dataTable']//td[./img]/a[@href]")
    private List<WebElement> ducks;

    public CatalogPage(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
        PageFactory.initElements(driverHere, this);
    }

    public void open() {
        driverHere.navigate().to(catalogPageUrl);
        driverHere.manage().window().maximize();
    }

    public CommonTable getCatalogTable() {
        return new CommonTable(driverHere, By.cssSelector("table.dataTable"),
                By.xpath(".//tr[not(@class='header') and not(@class = 'footer') and .//a[not(.='[Root]')]]"),
                By.cssSelector("td"));
    }

    private List<WebElement> getClosedFoldersFromCatalog() {
        return driverHere.findElements(By.xpath("//td[./i[@class = 'fa fa-folder']]"));
    }

    public List<WebElement> getAllDucks() {
        ///return driverHere.findElements(By.xpath("//*[@class='dataTable']//td[./img]/a[@href]"));
        return ducks;
    }

    public void openAllFoldersInCatalog() {
        WebDriverWait wait = new WebDriverWait(driverHere, 8);
        while (getClosedFoldersFromCatalog().size() > 0) {
            WebElement staleCategory = getClosedFoldersFromCatalog().get(0);
            staleCategory.findElement(By.xpath(".//a[@href]")).click();
            try {
                wait.until(ExpectedConditions.stalenessOf(staleCategory));
            }
            catch (TimeoutException toe) {
                //Very rare it does not not click without any reasonable reasons. That is why.
                getClosedFoldersFromCatalog().get(0).findElement(By.xpath(".//a[@href]")).click();
                wait.until(ExpectedConditions.stalenessOf(staleCategory));
            }
        }
    }

    public void clickAddNewProductButton() {
        addNewProductButton.click();
    }
}
