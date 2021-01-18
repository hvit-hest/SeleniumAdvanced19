package org.courses.pages.mainpage.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class DuckDetails {
    private WebElement rootDuckDetailsWe;
    private By duckNameBy = By.cssSelector(".name");
    private By duckRegularPriceBy = By.cssSelector(".regular-price,.price");
    private By duckCampaignPriceBy = By.cssSelector(".campaign-price");


    public DuckDetails(WebElement rootDuckDetailsWe) {
        this.rootDuckDetailsWe = rootDuckDetailsWe;
    }


    public HashMap<String, String> getDuckData() {
        HashMap<String, String> DuckDetails = new HashMap<String, String>();
        WebElement regularPriceWe = rootDuckDetailsWe.findElement(duckRegularPriceBy);

        DuckDetails.put("duckName", rootDuckDetailsWe.findElement(duckNameBy).getText());

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
            DuckDetails.put("campaignPrice","");
            DuckDetails.put("campaignPriceColor", "");
            DuckDetails.put("campaignPriceDecoration","");
            DuckDetails.put("campaignPriceFontWeight","");
        }

        return DuckDetails;
    }
}
