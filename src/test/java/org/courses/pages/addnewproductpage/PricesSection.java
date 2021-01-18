package org.courses.pages.addnewproductpage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.LinkedHashMap;

public class PricesSection {
    private WebDriver driverHere;
    private By purchasePriceFieldBy = By.cssSelector("input[name='purchase_price']");
    private By currencySelectionBy = By.cssSelector("select[name='purchase_price_currency_code']");
    private By taxClassSelectionBy = By.cssSelector("select[name='tax_class_id']");
    private By priceUSDFieldBy = By.cssSelector("input[name='prices[USD]']");
    private By priceEUROFieldBy = By.cssSelector("input[name='prices[EUR]']");

    public PricesSection(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
    }

    public Select getDropDown(By locatorBy) {
        return new Select(driverHere.findElement(locatorBy));
    }

    public void fillFormProductPrices(LinkedHashMap<String, String> pricesData) {

        Wait<WebDriver> wait = new WebDriverWait(driverHere, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(priceEUROFieldBy));

        pricesData.entrySet().forEach(data -> {
            String dataValue = data.getValue();

            if (!dataValue.isEmpty())

                switch (data.getKey()) {
                    case "purchasePrice":
                        driverHere.findElement(purchasePriceFieldBy).clear();
                        driverHere.findElement(purchasePriceFieldBy).sendKeys(dataValue);
                        break;
                    case "currency":
                        getDropDown(currencySelectionBy).selectByVisibleText(dataValue);
                        break;
                    case "taxClass":
                        driverHere.findElement(taxClassSelectionBy).sendKeys(dataValue);
                        break;
                    case "priceUSD":
                        driverHere.findElement(priceUSDFieldBy).sendKeys(dataValue);
                        break;
                    case "priceEURO":
                        driverHere.findElement(priceEUROFieldBy).sendKeys(dataValue);
                        break;
                    default:
                        System.out.println(String.format("The data '%s' : '%s' does not exist",
                                data.getKey(), data.getValue()));
                }
        });
    }
}
