package org.courses.pages.addnewproductpage.data;

import java.util.LinkedHashMap;

public class ProductDataModel {
    private GeneralDataModel generalData;
    private InformationDataModel informationData;
    private PricesDataModel pricesData;

    public LinkedHashMap<String, Object> getGeneralData() {
        return generalData.getGeneralData();
    }
    public LinkedHashMap<String, String> getInformationData() {
        return informationData.getInformationData();
    }
    public LinkedHashMap<String, String> getPricesData() {return pricesData.getPricesData(); }
}
