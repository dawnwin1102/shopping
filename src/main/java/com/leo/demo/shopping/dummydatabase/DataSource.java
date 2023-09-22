package com.leo.demo.shopping.dummydatabase;

import java.util.HashMap;
import java.util.Map;

public class DataSource {
    private static Map<String, Map<String, String>> data = new HashMap<>();

    static {
        Map<String, String> data1 = new HashMap<>();
        data1.put("password", "mike123");
        data1.put("role", "user");
        data1.put("permission", "view");
        data.put("mike", data1);
        Map<String, String> data2 = new HashMap<>();
        data2.put("password", "leo123");
        data2.put("role", "admin");
        data2.put("permission", "view,edit");
        data.put("leo", data2);
    }

    public static Map<String, Map<String, String>> getData() {
        return data;
    }
}
