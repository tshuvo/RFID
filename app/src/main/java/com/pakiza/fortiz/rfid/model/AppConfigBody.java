package com.pakiza.fortiz.rfid.model;

public class AppConfigBody {

    String BaseUrl;
    String ImagePath;
    String CompanyName;
    String Logo;

    public AppConfigBody(String baseUrl, String imagePath, String companyName, String logo) {
        BaseUrl = baseUrl;
        ImagePath = imagePath;
        CompanyName = companyName;
        Logo = logo;
    }
}
