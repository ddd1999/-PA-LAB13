package com;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SetLocale {
    /**
     * Sets the current locale
     *
     * @param locale
     * @return
     */
    public Locale cmd(Locale locale) {
        setLocaleCommand(locale);

        Scanner scanner = new Scanner(System.in);
        String newLocale = scanner.nextLine();

        Locale[] locales = SimpleDateFormat.getAvailableLocales();

        for (int i = 0; i < locales.length; i++) {
            if (locales[i].toString().equals(newLocale)) {
                return locales[i];
            }
        }

        return locale;
    }

    /**
     * Gets the message associated with the "set" command
     *
     * @param locale
     */
    private static void setLocaleCommand(Locale locale) {
        String baseName = "Messages";
        ResourceBundle messages =
                ResourceBundle.getBundle(baseName, locale);
        String formatted = new MessageFormat(messages.getString("locale.set")).format(new Object[]{locale.getCountry()});
        System.out.println(formatted);
    }
}
