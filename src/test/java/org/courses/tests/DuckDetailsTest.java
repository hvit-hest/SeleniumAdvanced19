package org.courses.tests;

import org.courses.pages.duckdetailspage.DuckDetailsPage;
import org.courses.pages.duckdetailspage.components.DuckDetailsSection;
import org.courses.pages.mainpage.MainPage;
import org.courses.utils.Utils;
import org.courses.utils.WebDriverSelection;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.courses.pages.mainpage.components.DucksBlock.CAMPAIGNS;

public class DuckDetailsTest {
    private WebDriver myPersonalDriver;
    private String userEmail;
    private String userPassword;
    private MainPage mainPage;

    @BeforeClass
    public void beforeClass() {
        userPassword = new Utils().getTestProperties().getProperty("userPassword");
        userEmail = new Utils().getTestProperties().getProperty("userEmail");
        myPersonalDriver = new WebDriverSelection().getDriverFromProperties();
        myPersonalDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Test
    public void duckDetailsTest() {
        mainPage = new MainPage(myPersonalDriver);
        mainPage.open();
        mainPage.login(userEmail, userPassword);

        SoftAssert softAssertion = new SoftAssert();

        //It was said to check picture/data of first duck in Campaign on Main Page
        Map<String, String> duckData = mainPage.getDuck(CAMPAIGNS, 0).getDuckData();
        String duckOnMainPageName = duckData.get("duckName");
        String duckOnMainPageRegularPrice = duckData.get("regularPrice");
        String duckOnMainPageRegularColor = duckData.get("regularPriceColor");
        String duckOnMainPageRegularPriceDecoration = duckData.get("regularPriceDecoration");
        String duckOnMainPageCampaignPrice = duckData.get("campaignPrice");
        String duckOnMainPageCampaignPriceColor = duckData.get("campaignPriceColor");
        String duckOnMainPageCampaignFontWeight = duckData.get("campaignPriceFontWeight");

        //Asserts for MainPage

        //regular price is grey on MainPage
        List<Integer> regularPriceIntMainPage = Utils.getNumberOfColor(duckOnMainPageRegularColor);
        softAssertion.assertTrue(
                regularPriceIntMainPage.get(0) == regularPriceIntMainPage.get(1) &&
                        regularPriceIntMainPage.get(1) == regularPriceIntMainPage.get(2),
                String.format("Colour is grey when the numbers '%s' '%s' '%s' are equal, Main Page",
                        regularPriceIntMainPage.get(0),
                        regularPriceIntMainPage.get(1),
                        regularPriceIntMainPage.get(2)));

        //regular price is crossed out on MainPage
        softAssertion.assertTrue(duckOnMainPageRegularPriceDecoration.contains("line-through"),
                "Regular price is NOT crossed out on Main Page");

        //campaign price is red of color on MainPage
        List<Integer> campaignPriceColorsIntMainPage = Utils.getNumberOfColor(duckOnMainPageCampaignPriceColor);
        softAssertion.assertTrue(
                campaignPriceColorsIntMainPage.get(1) == 0 &&
                        campaignPriceColorsIntMainPage.get(2) == 0,
                String.format("Colour is red when the numbers '%s' '%s' are 0, DuckDetails Page",
                        campaignPriceColorsIntMainPage.get(1),
                        campaignPriceColorsIntMainPage.get(2)));

        //campaign price has "bold" font-weight on MainPage
        softAssertion.assertTrue(
                duckOnMainPageCampaignFontWeight.equals("bold") ||
                        Integer.parseInt(duckOnMainPageCampaignFontWeight) >= 700,
                "Campaign price is NOT bold out on Main Page");

        //Compare regular price and campaign price on MainPage
        softAssertion.assertTrue(Utils.comparePrices(duckOnMainPageCampaignPrice, duckOnMainPageRegularPrice) < 0,
                String.format("Campaign price '%s' has to be less than regular price '%s', Main Page",
                        duckOnMainPageCampaignPrice, duckOnMainPageRegularPrice));

        //Click!
        //It was said to check first duck in Campaign so click it
        mainPage.openParticularDuckDetailsPage(CAMPAIGNS, 0);


        //Get data from DuckDetailsPage
        DuckDetailsPage duckDetailsPage = new DuckDetailsPage(myPersonalDriver);
        DuckDetailsSection duckDetailsSection = duckDetailsPage.getDuckDetailsSection();
        Map<String, String> duckDataOnDuckDetailsPage = duckDetailsSection.getDuckData();

        String duckOnDetailsPageName = duckDetailsPage.getDuckName();
        String duckOnDetailsPageRegularPrice = duckDataOnDuckDetailsPage.get("regularPrice");
        String duckOnDetailsPageRegularPriceColor = duckDataOnDuckDetailsPage.get("regularPriceColor");
        String duckOnDetailsPageRegularPriceDecoration = duckDataOnDuckDetailsPage.get("regularPriceDecoration");
        String duckOnDetailsPageCampaignPrice = duckDataOnDuckDetailsPage.get("campaignPrice");
        String duckOnDetailsPageCampaignPriceColor = duckDataOnDuckDetailsPage.get("campaignPriceColor");
        String duckOnDetailsPageCampaignFontWeight = duckData.get("campaignPriceFontWeight");


        //Asserts for DuckDetails Page

        //regular price is grey of color on DuckDetails Page
        List<Integer> regularPriceColorsIntDetailsPage = Utils.getNumberOfColor(duckOnDetailsPageRegularPriceColor);
        softAssertion.assertTrue(
                regularPriceColorsIntDetailsPage.get(0) == regularPriceColorsIntDetailsPage.get(1) &&
                        regularPriceColorsIntDetailsPage.get(1) == regularPriceColorsIntDetailsPage.get(2),
                String.format("Colour is grey when the numbers '%s' '%s' '%s' are equal, DuckDetails Page",
                        regularPriceColorsIntDetailsPage.get(0),
                        regularPriceColorsIntDetailsPage.get(1),
                        regularPriceColorsIntDetailsPage.get(2))
        );

        //regular price is crossed out on DuckDetails Page
        softAssertion.assertTrue(duckOnDetailsPageRegularPriceDecoration.contains("line-through"),
                "Regular price is NOT crossed out on Details Page");

        //campaign price is red of color
        List<Integer> campaignPriceColorsIntDetailsPage = Utils.getNumberOfColor(duckOnDetailsPageCampaignPriceColor);
        softAssertion.assertTrue(
                campaignPriceColorsIntDetailsPage.get(1) == 0 &&
                        campaignPriceColorsIntDetailsPage.get(2) == 0,
                String.format("Colour is red when the numbers '%s' '%s' are 0, DuckDetails Page",
                        regularPriceColorsIntDetailsPage.get(1),
                        regularPriceColorsIntDetailsPage.get(2)));

        //campaign price has "bold" font-weight on DuckDetails Page
        softAssertion.assertTrue(
                duckOnDetailsPageCampaignFontWeight.equals("bold") ||
                        Integer.parseInt(duckOnDetailsPageCampaignFontWeight) >= 700,
                "Campaign price is NOT bold out on DuckDetails Page");

        //Compare regular price and campaign price on DuckDetails Page

        softAssertion.assertTrue(Utils.comparePrices(duckOnDetailsPageCampaignPrice, duckOnDetailsPageRegularPrice) < 0,
                String.format("Campaign price '%s' has to be less than regular price '%s', DuckDetails Page",
                        duckOnDetailsPageCampaignPrice, duckOnDetailsPageRegularPrice));


        //Asserts to compare Duck's prices on both pages - Main page and DuckDetails page
        //Compare ducks' names
        softAssertion.assertEquals(duckOnMainPageName, duckOnDetailsPageName,
                String.format("'%s' vs '%s' Ducks' names are different", duckOnMainPageName, duckOnDetailsPageName));

        //Compare regular prices on both pages

        softAssertion.assertTrue(Utils.comparePrices(duckOnDetailsPageRegularPrice, duckOnMainPageRegularPrice) == 0,
                String.format("Regular price '%s' on Main page  has to be equal regular price '%s' on DuckDetails page",
                        duckOnMainPageRegularPrice, duckOnDetailsPageRegularPrice));

        //Compare campaign prices on both pages

        softAssertion.assertTrue(Utils.comparePrices(duckOnDetailsPageCampaignPrice, duckOnMainPageCampaignPrice) == 0,
                String.format("Campaign price '%s' on Main page  has to equal campaign price '%s' on DuckDetails page",
                        duckOnMainPageCampaignPrice, duckOnDetailsPageCampaignPrice));

        softAssertion.assertAll();

    }

    @AfterClass
    public void afterClass() {
        myPersonalDriver.quit();
    }

}

