package org.courses.pages.duckdetailspage.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;

public class DuckDetailsSection {

    private WebDriver driverHere;
    private WebElement rootDuckDetailsWe;
    private By rootDuckDetailsBy = By.cssSelector("#box-product");
    private By duckRegularPriceBy = By.cssSelector(".regular-price,.price");
    private By duckCampaignPriceBy = By.cssSelector(".campaign-price");
    private By addToCartButtonBy = By.cssSelector("button[name='add_cart_product']");
    private By quantityFieldBy = By.cssSelector("input[name='quantity']");
    private By sizeSelectionBy = By.cssSelector("select[name='options[Size]']");

    public DuckDetailsSection(WebElement rootDuckDetailsWe) {
        this.rootDuckDetailsWe = rootDuckDetailsWe;
    }

    public DuckDetailsSection(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
        this.rootDuckDetailsWe = driverHere.findElement(rootDuckDetailsBy);
    }


    public HashMap<String, String> getDuckData() {
        HashMap<String, String> DuckDetails = new HashMap<String, String>();
        WebElement regularPriceWe = rootDuckDetailsWe.findElement(duckRegularPriceBy);

        DuckDetails.put("regularPrice", regularPriceWe.getText());
        DuckDetails.put("regularPriceColor", regularPriceWe.getCssValue("color"));
        DuckDetails.put("regularPriceDecoration", regularPriceWe.getCssValue("text-decoration"));
        DuckDetails.put("regularPriceFontWeight", regularPriceWe.getCssValue("font-weight"));

        List<WebElement> campaignPriceWEs = rootDuckDetailsWe.findElements(duckCampaignPriceBy);

        if (campaignPriceWEs.size() > 0) {
            DuckDetails.put("campaignPrice", campaignPriceWEs.get(0).getText());
            DuckDetails.put("campaignPriceColor", campaignPriceWEs.get(0).getCssValue("color"));
            DuckDetails.put("campaignPriceDecoration", campaignPriceWEs.get(0).getCssValue("text-decoration"));
            DuckDetails.put("campaignPriceFontWeight", campaignPriceWEs.get(0).getCssValue("font-weight"));
        } else {
            DuckDetails.put("campaignPrice", "");
            DuckDetails.put("campaignPriceColor", "");
            DuckDetails.put("campaignPriceDecoration", "");
            DuckDetails.put("campaignPriceFontWeight", "");
        }

        return DuckDetails;
    }

    public Select getDropDown(By locatorBy) {
        return new Select(rootDuckDetailsWe.findElement(locatorBy));
    }

    public void addDuckToCart(String size, int quantity) {
        if (rootDuckDetailsWe.findElements(sizeSelectionBy).size() > 0)
            getDropDown(sizeSelectionBy).selectByVisibleText(size);
        WebElement quantityWe = rootDuckDetailsWe.findElement(quantityFieldBy);
        quantityWe.clear();
        quantityWe.sendKeys(Integer.toString(quantity));

        pressAddToCartButton();
    }

    public void addDuckToCart(int quantity) {

        WebElement quantityWe = rootDuckDetailsWe.findElement(quantityFieldBy);
        quantityWe.clear();
        quantityWe.sendKeys(Integer.toString(quantity));
        if (rootDuckDetailsWe.findElements(sizeSelectionBy).size() > 0)
            getDropDown(sizeSelectionBy).selectByVisibleText("Small");

        pressAddToCartButton();
    }

    public void pressAddToCartButton() {
        rootDuckDetailsWe.findElement(addToCartButtonBy).click();
    }
}

