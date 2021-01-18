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

public class InformationSection {

    private WebDriver driverHere;

    private By manufacturerSelectionBy = By.cssSelector("select[name='manufacturer_id']");
    private By supplierSelectionBy = By.cssSelector("select[name='supplier_id']");
    private By keyWordsFieldBy = By.cssSelector("input[name='keywords']");
    private By shortDescriptionFieldBy = By.cssSelector("input[name^='short_description']");
    private By descriptionTextAreaBy = By.cssSelector(".trumbowyg-editor");
    private By headTitleFieldBy = By.cssSelector("input[name^='head_title']");
    private By metaDescriptionFieldBy = By.cssSelector("input[name^='meta_description']");

    public InformationSection(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
    }

    public Select getDropDown(By locatorBy) {
        return new Select(driverHere.findElement(locatorBy));
    }

    public void fillFormProductInformation(LinkedHashMap<String, String> informationData) {

        Wait<WebDriver> wait = new WebDriverWait(driverHere, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(metaDescriptionFieldBy));

        informationData.entrySet().forEach(data -> {
            String dataValue = data.getValue();

            if (!dataValue.isEmpty())

                switch (data.getKey()) {
                    case "manufacturer":
                        getDropDown(manufacturerSelectionBy).selectByVisibleText(dataValue);
                        break;
                    case "supplier":
                        getDropDown(supplierSelectionBy).selectByVisibleText(dataValue);
                        break;
                    case "keyWords":
                        driverHere.findElement(keyWordsFieldBy).sendKeys(dataValue);
                        break;
                    case "shortDescription":
                        driverHere.findElement(shortDescriptionFieldBy).sendKeys(dataValue);
                        break;
                    case "description":
                        driverHere.findElement(descriptionTextAreaBy).click();
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        //Take it from buffer
                        clipboard.setContents(new StringSelection(dataValue), null);
                        ;
                        driverHere.findElement(descriptionTextAreaBy).sendKeys(Keys.SHIFT, Keys.INSERT);
                        break;
                    case "headTitle":
                        driverHere.findElement(headTitleFieldBy).sendKeys(dataValue);
                        break;
                    case "metaDescription":
                        driverHere.findElement(metaDescriptionFieldBy).sendKeys(dataValue);
                        break;
                    default:
                        System.out.println(String.format("The data '%s' : '%s' does not exist",
                                data.getKey(), data.getValue()));
                }
        });
    }
}
