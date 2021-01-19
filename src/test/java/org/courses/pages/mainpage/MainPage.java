package org.courses.pages.mainpage;

import org.courses.pages.mainpage.components.DuckDetails;
import org.courses.pages.mainpage.components.DucksBlock;
import org.courses.pages.mainpage.components.UserLoginForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage {

    private String mainPageUrl = "http://192.168.100.8/litecart/en/";
    private WebDriver driverHere;

    @FindBy(css = "#rslides1_s0")
    private WebElement bigDuckPicture;

    @FindBy(css = "ul.products>li.product")
    private List<WebElement> duckProducts;

    @FindBy(xpath = "//aside//li/a[.='Logout']")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[contains(., 'New customer')]")
    private WebElement newCustomerLink;

    @FindBy(xpath = "//*[@id='cart']//a[contains(.,'Checkout')]")
    private WebElement checkOutButton;


    public MainPage(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
        PageFactory.initElements(driverHere, this);
    }

    public void open() {
        driverHere.navigate().to(mainPageUrl);
        driverHere.manage().window().maximize();
    }

    public void login(String userEmail, String userPassword) {
        if (isUserLoginFormOpened()) {
            new UserLoginForm(driverHere).login(userEmail, userPassword);
        }
    }

    public boolean isUserLoginFormOpened() {
        return driverHere.findElements(By.cssSelector("form[name='login_form']")).size() > 0;
    }

    public boolean isLogoutButtonPresent() {
        return driverHere.findElements(By.cssSelector("#box-account [href$='logout']")).size() > 0;
    }


    public List<WebElement> getAllDuckProducts() {
        return duckProducts;
    }

    public void openNthDuckDetails(int i) {
        getAllDuckProducts().get(i).click();
    }

    public void logout() {
        if (isLogoutButtonPresent()) {
            Wait<WebDriver> wait = new WebDriverWait(driverHere, 10);
            wait.until(ExpectedConditions.
                    elementToBeClickable(driverHere.findElement(By.cssSelector("#box-account [href$='logout']"))));
            driverHere.findElement(By.cssSelector("#box-account [href$='logout']")).click();
        }
    }

    public List<WebElement> getDucksWEs(DucksBlock ducksBlock) {
        return driverHere.findElement(ducksBlock.getBlockSelector()).
                findElements(By.cssSelector("li.product"));
    }


    public DuckDetails getDuck(DucksBlock ducksBlock, int numberInList) {
        DuckDetails duckDetails = null;
        List<WebElement> ducksList = getDucksWEs(ducksBlock);
        if (ducksList.size() != 0)
            duckDetails = new DuckDetails(ducksList.get(numberInList));
        return duckDetails;
    }

    public void openParticularDuckDetailsPage(DucksBlock ducksBlock, int numberInList) {
        List<WebElement> getDucksWEs = getDucksWEs(ducksBlock);
        if (getDucksWEs.size() > 0) {
            getDucksWEs.get(numberInList).click();
        }
    }

    public void clickNewCustomerLink() {
        newCustomerLink.click();
    }

    public void clickCheckOutLink() {
        checkOutButton.click();
    }
}
