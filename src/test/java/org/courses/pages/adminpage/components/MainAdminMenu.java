package org.courses.pages.adminpage.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MainAdminMenu {

    private WebDriver myPersonalDriver;
    private String searchForMainItemXpath = "//li//span[contains(@class,'icon')]/following-sibling::span[.='%s']";
    private String searchForSubMenuItemXpath = "//li[starts-with(@id,'doc')]/*[.='%s']";

    public MainAdminMenu(WebDriver driver) {
        this.myPersonalDriver = driver;
    }

    public void selectMenuOption(String menuOption) {
        selectMenuOption(menuOption, searchForMainItemXpath);
    }

    public void selectSubMenuOption(String subMenuOption) {
        selectMenuOption(subMenuOption, searchForSubMenuItemXpath);
    }


    private void selectMenuOption(String menuOption, String searchMenuOptionXpath) {
        WebElement menuOptionWe = myPersonalDriver.
                findElement(By.xpath(String.format(searchMenuOptionXpath, menuOption)));

        menuOptionWe.click();

        /*Sometimes driver is not clicking (very rare) without any message and reason when moving from bottom item of menu
        to top item.  That is why the second try.
         */
        menuOptionWe = myPersonalDriver.
                findElement(By.xpath(String.format(searchMenuOptionXpath, menuOption)));
        if (!menuOptionWe.getCssValue("color").equals("rgba(255, 0, 0, 1)")) {
            myPersonalDriver.navigate().refresh();
            myPersonalDriver.findElement(By.xpath(String.format(searchMenuOptionXpath, menuOption))).click();
        }
    }
}
