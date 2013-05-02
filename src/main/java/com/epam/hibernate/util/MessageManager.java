package com.epam.hibernate.util;

import java.util.ResourceBundle;

public final class MessageManager {
    private static ResourceBundle resource = ResourceBundle.getBundle("com.epam.hibernate.resources.message");

    public static String getStr(String key) {
        return resource.getString(key);
    }
}
