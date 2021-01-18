package org.courses.pages.addnewproductpage.components;

import org.openqa.selenium.By;

public enum NewProductTabs {
    GENERAL(By.xpath("//ul[@class='index']/li/a[.='General']")),
    INFORMATION(By.xpath("//ul[@class='index']/li/a[.='Information']")),
    DATA(By.xpath("//ul[@class='index']/li/a[.='Data']")),
    PRICES(By.xpath("//ul[@class='index']/li/a[.='Prices']")),
    OPTIONS(By.xpath("//ul[@class='index']/li/a[.='Options']")),
    OPTION_STOCK(By.xpath("//ul[@class='index']/li/a[.='Options Stock']"));

    private By tabBy;

    NewProductTabs(By tabBy){
        this.tabBy = tabBy;
    }
    public By getTabBy() {
        return tabBy;
    }
}
