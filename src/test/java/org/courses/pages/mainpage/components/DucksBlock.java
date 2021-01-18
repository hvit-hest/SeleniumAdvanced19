package org.courses.pages.mainpage.components;

import org.openqa.selenium.By;

public enum DucksBlock {
    MOST_POPULAR (By.cssSelector("#box-most-popular")),
    CAMPAIGNS (By.cssSelector("#box-campaigns")),
    LATEST_PRODUCTS (By.cssSelector("#box-latest-products"));

    private By blockSelector;

    DucksBlock(By blockSelector) {
        this.blockSelector = blockSelector;
    }

    public By getBlockSelector() {
        return blockSelector;
    }
};
