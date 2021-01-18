package org.courses.pages.addnewproductpage.data;

import org.apache.commons.lang3.RandomStringUtils;
import org.courses.utils.Utils;

import java.util.LinkedHashMap;

public class GeneralDataModel {

    private String status;
    private String name;
    private String code;
    private String[] categories;
    private String defaultCategory;
    private String[] productGroups;
    private String quantity;
    private String quantityUnit;
    private String deliveryStatus;
    private String soldOutStatus;
    private String[] uploadImages;
    private String dateValidFrom;
    private String dateValidTo;

    public LinkedHashMap<String, Object> getGeneralData() {
        return new LinkedHashMap<String, Object>() {{
            put("status", status);
            put("name", name.toLowerCase().equals("generated") ? RandomStringUtils.randomAlphabetic(4, 16) : name);
            put("code", code.toLowerCase().equals("generated") ? RandomStringUtils.randomNumeric(4, 10) : code);
            put("categories", categories);
            put("defaultCategory", defaultCategory);
            put("productGroups", productGroups);
            put("quantity", quantity.toLowerCase().equals("generated") ? RandomStringUtils.randomNumeric(1, 5) : quantity);
            put("quantityUnit", quantityUnit);
            put("deliveryStatus", deliveryStatus);
            put("soldOutStatus", soldOutStatus);
            put("uploadImages", uploadImages);
            put("dateValidFrom", dateValidFrom);
            put("dateValidTo", dateValidTo);
        }};
    }
}
