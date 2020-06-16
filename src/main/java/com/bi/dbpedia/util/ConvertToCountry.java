package com.bi.dbpedia.util;

import java.util.HashMap;
import java.util.Map;

public class ConvertToCountry {

    private static Map<String, String> cMap = new HashMap<>();

    static {
        cMap.put("United_States", "United States");
        cMap.put("California", "United States");
        cMap.put("Delaware", "United States");
        cMap.put("Washington", "United States");
        cMap.put("New_York", "United states");
        cMap.put("Seattle", "United States");
        cMap.put("Los_Angeles", "United States");
        cMap.put("Austin,_TX", "United States");
        cMap.put("Connecticut", "United States");
        cMap.put("Wallingford,_Connecticut", "United States");
        cMap.put("Mountain_View", "United States");
        cMap.put("Cambridge", "United States");
        cMap.put("Michigan", "United States");
        cMap.put("Pittsburgh,_Pennsylvania", "United State");
        cMap.put("San_Francisco", "United_States");

        cMap.put("United_Kingdom", "United_Kingdom");
        cMap.put("London", "United_Kingdom");
        cMap.put("Thirsk,_North_Yorkshire", "United_Kingdom");

        cMap.put("Halifax_Regional_Municipality", "Canada");
        cMap.put("Montreal", "Canada");
        cMap.put("Québec", "Canada");
        cMap.put("Toronto", "Canada");
        cMap.put("Ontario", "Canada");

        cMap.put("Tokyo", "Japan");
        cMap.put("Kanagawa,_Japan", "Japan");

        cMap.put("Moscow", "Russia");

        cMap.put("Bangalore", "India");
        cMap.put("Karnataka", "India");
        cMap.put("Delhi", "India");
        cMap.put("Kochi,_India", "India");

        cMap.put("Madrid", "Spain");

        cMap.put("Kingston,_Jamaica", "Jamaica");

        cMap.put("New_Zealand", "New Zealand");
        cMap.put("Auckland", "Auckland");

        cMap.put("Oulu", "Finland");

        cMap.put("Rotterdam", "Netherlands");
        cMap.put("Beijing", "China");

        cMap.put("Tallinn", "Estonia");

        cMap.put("Zurich", "Switzerland");

        cMap.put("Riyadh", "Saudi Arabia");

        cMap.put("Universidade_de_Aveiro", "Portugal");

    }

    public static String convert(String origin) {
        if (origin.contains("California")) {
            origin = "California";
        }
        if (origin.contains("Washington")) {
            origin = "Washington";
        }
        if (origin.contains("New_York")) {
            origin = "New_York";
        }

        if (origin.contains("Australia")) {
            origin = "Australia";
        }
        String country = cMap.get(origin);
        return country != null ? country : origin;
    }
}
