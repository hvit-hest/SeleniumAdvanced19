package org.courses.pages.createaccountpage.data;
import org.apache.commons.lang3.RandomStringUtils;
import org.courses.utils.Utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;



public class AccountFormData {

    private String taxID =  RandomStringUtils.randomNumeric(6);
    private String company = String.format("%s %s", Utils.nameGenerator(), Utils.nameGenerator());
    private String firstName = Utils.nameGenerator();
    private String lastName = Utils.nameGenerator();
    private String address1 = Utils.addressGenerator();
    private String address2 = Utils.addressGenerator();
    private String postCode = RandomStringUtils.randomNumeric(5);
    private String city = "Salem";
    private String country = "United States";
    private String stateZoneProvince = "Oregon";
    private String email = Utils.emailGenerator();
    private String phone = Utils.phoneGenerator();;
    private String subscribe = "checked";
    private String password = email.split("@")[0];
    private String  passwordConfirmed = password;
    private LinkedHashMap<String, String> accountDataMap;
    public AccountFormData() {
        this.accountDataMap  = new LinkedHashMap<String, String>() {{
            put("taxID", taxID);
            put("company", company);
            put("firstName", firstName);
            put("lastName", lastName);
            put("address1", address1);
            put("address2", address2);
            put("postCode", postCode);
            put("city", city);
            put("country", country);
            put("stateZoneProvince", stateZoneProvince);
            put("email", email);
            put("phone", phone);
            put("subscribe", subscribe);
            put("password", password);
            put("passwordConfirmed", passwordConfirmed);
        }};
    }

    public AccountFormData(String city, String country, String stateZoneProvince, String subscribe) {
        this.city = city;
        this.country = country;
        this.stateZoneProvince = stateZoneProvince;
        this.subscribe = subscribe;

        this.accountDataMap  = new LinkedHashMap<String, String>() {{
            put("taxID", taxID);
            put("company", company);
            put("firstName", firstName);
            put("lastName", lastName);
            put("address1", address1);
            put("address2", address2);
            put("postCode", postCode);
            put("city", city);
            put("country", country);
            put("stateZoneProvince", stateZoneProvince);
            put("email", email);
            put("phone", phone);
            put("subscribe", subscribe);
            put("password", password);
            put("passwordConfirmed", passwordConfirmed);
        }};
    }

    public LinkedHashMap<String, String> getData() {return accountDataMap; }

    public String getTaxID() { return taxID;  }
    public String getCompany() { return company;  }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress1() {return address1; }
    public String getAddress2() {return address2; }
    public String getPostcode() {return postCode; }
    public String getCity() { return city;  }
    public String getCountry() { return country;  }
    public String getStateZoneProvince() { return stateZoneProvince; }
    public String getEmail() { return email;  }
    public String getPhone() { return phone; }
    public String getSubscribe() {return subscribe; }
    public String getPassword() {return password; }
    public String getPasswordConfirmed() {return passwordConfirmed; }
}
