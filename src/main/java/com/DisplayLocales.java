package com;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales {
    /**
     * Displays the available languages
     *
     * @param locale
     */
    public void cmd(Locale locale) {
        displayLocalesCommand(locale);
        System.out.println("default");
        System.out.println("ro");
    }

    /**
     * Returns the message associated with the "display" command
     *
     * @param locale
     */
    private static void displayLocalesCommand(Locale locale) {
        String baseName = "Messages";
        ResourceBundle messages =
                ResourceBundle.getBundle(baseName, locale);
        System.out.println(messages.getString("locales"));
    }
}
