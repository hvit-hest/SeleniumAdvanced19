package org.courses.pages.addnewproductpage.data;

import org.courses.utils.Utils;

import java.util.LinkedHashMap;

public class InformationDataModel {

    private String manufacturer;
    private String supplier;
    private String keyWords;
    private String shortDescription;
    private String description;
    private String headTitle;
    private String metaDescription;

    public LinkedHashMap<String, String> getInformationData() {
        return new LinkedHashMap<String, String>() {{
            put("manufacturer", manufacturer);
            put("supplier", supplier);
            put("keyWords", keyWords.toLowerCase().equals("generated") ? Utils.wordsGenerator() : keyWords);
            put("shortDescription", shortDescription.toLowerCase().equals("generated") ?
                    Utils.wordsGenerator() : shortDescription);
            put("description", description.toLowerCase().equals("generated") ?
                    Utils.wordsGenerator() : shortDescription);
            put("headTitle", headTitle.toLowerCase().equals("generated") ?
                    Utils.wordsGenerator() : headTitle);
            put("metaDescription", metaDescription.toLowerCase().equals("generated") ?
                    Utils.wordsGenerator() : metaDescription);
        }};
    }
}
