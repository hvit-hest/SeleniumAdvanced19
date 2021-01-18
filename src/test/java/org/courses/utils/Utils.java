package org.courses.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.courses.pages.addnewproductpage.data.ProductDataModel;
import org.courses.pages.adminpage.data.MenuItemModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class Utils {

    public void printTestingProperties() {
        Properties prop = loadPropertiesFile("testconfig.properties");
        prop.forEach((k, v) -> System.out.println(k + " " + v));
    }

    private Properties loadPropertiesFile(String filePath) {
        Properties prop = new Properties();
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            prop.load(resourceAsStream);
        } catch (IOException ioe) {
            System.err.println("Unable to load properties file:" + filePath);
        }
        return prop;
    }

    public Properties getTestProperties() {
        return loadPropertiesFile("testconfig.properties");
    }

    public List<MenuItemModel> readMenuFromJson(String jsonArrayFile) {
        Type menuType = new TypeToken<List<MenuItemModel>>() {
        }.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(jsonArrayFile);
            reader = new JsonReader(new InputStreamReader(resourceAsStream));
        } catch (NullPointerException ioe) {
            System.err.println("Unable to read json file:" + jsonArrayFile);
        }
        return gson.fromJson(reader, menuType);
    }

    public ProductDataModel readProductDataFromJson(String jsonArrayFile) {
        Type productDataType = new TypeToken<ProductDataModel>() {
        }.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(jsonArrayFile);
            reader = new JsonReader(new InputStreamReader(resourceAsStream));
        } catch (NullPointerException ioe) {
            System.err.println("Unable to read json file:" + jsonArrayFile);
        }
        return gson.fromJson(reader, productDataType);
    }

    public Map<String, String> readAddWindowTestDataFromJson(String jsonArrayFile) {
        Type addWindowDataType = new TypeToken<Map<String, String>>() {
        }.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(jsonArrayFile);
            reader = new JsonReader(new InputStreamReader(resourceAsStream));
        } catch (NullPointerException ioe) {
            System.err.println("Unable to read json file:" + jsonArrayFile);
        }
        return gson.fromJson(reader, addWindowDataType);
    }

    public static List<Integer> getNumberOfColor(String color) {
        Pattern pattern = Pattern.compile("\\d+");
        List<Integer> list = new ArrayList<Integer>();
        Matcher m = pattern.matcher(color);
        while (m.find()) {
            list.add(Integer.parseInt(m.group()));
        }
        return list;
    }

    public static int comparePrices(String priceOne, String priceTwo) {
        BigDecimal firstPrice = new BigDecimal(priceOne.replaceAll("[^0-9.]", "")).
                setScale(2, ROUND_HALF_UP);
        BigDecimal secondPrice = new BigDecimal(priceTwo.replaceAll("[^0-9.]", "")).
                setScale(2, ROUND_HALF_UP);

        return firstPrice.compareTo(secondPrice);
    }

    public static String emailGenerator() {
        String[] forMail = UUID.randomUUID().toString().split("-");
        String topDomain = RandomStringUtils.randomAlphabetic(2, 8);
        return String.format("user%s@mail%s.%s", forMail[0], forMail[1], topDomain);
    }

    public static String nameGenerator() {
        return StringUtils.capitalize(RandomStringUtils.randomAlphabetic(1, 20).toLowerCase());
    }

    public static String addressGenerator() {
        return wordsGenerator();
    }

    public static String phoneGenerator() {
        return String.format("+%s", RandomStringUtils.randomNumeric(8));
    }

    public static String wordsGenerator() {
        return String.format("%s %s %s %s",
                RandomStringUtils.randomAlphanumeric(1, 15),
                RandomStringUtils.randomAlphanumeric(1, 15),
                RandomStringUtils.randomAlphanumeric(1, 15),
                RandomStringUtils.randomAlphanumeric(1, 15));
    }
}

