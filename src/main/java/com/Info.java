package com;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    /**
     * Displays specific information about the country
     *
     * @param locale
     */
    public void cmd(Locale locale) {
        System.out.println("country: " + locale.getCountry());
        infoLocaleCommand(locale);

        System.out.println("Country: " + locale.getDisplayCountry());

        System.out.println("Language: " + locale.getDisplayLanguage());

        try {
            Currency currency = Currency.getInstance(locale);
            String currencyStr = currency.getDisplayName(locale);
            System.out.println("Currency: " + currencyStr);
        } catch (Exception ignored) {
        }

        DateFormatSymbols dfs = new DateFormatSymbols(locale);
        String[] weekdays = dfs.getWeekdays();

        System.out.print("Week Days: ");
        Calendar calendar = Calendar.getInstance(locale);
        int firstDayOfWeek = calendar.getFirstDayOfWeek();
        for (int i = firstDayOfWeek; i <= firstDayOfWeek + 7; ++i) {
            System.out.print(weekdays[i % 7] + ", ");
        }
        System.out.println();

        String[] months = dfs.getMonths();
        System.out.print("Months: ");
        for (int i = 0; i < 12; ++i) {
            System.out.print(months[i] + ", ");
        }
        System.out.println();

        System.out.print("Today: ");
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale);
        System.out.println(today.format(formatter));

        String responseMessage = httpRequest("http://www.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/FullCountryInfo/JSON/debug?sCountryISOCode=", locale.getCountry());
        JSONObject obj = new JSONObject(responseMessage);

        System.out.println("Continent: " + obj.getString("sContinentCode"));
        System.out.println("Capital: " + obj.getString("sCapitalCity"));
        System.out.println("Phone Code: " + obj.getString("sPhoneCode"));
    }

    /**
     * Sends a http request to the url mentioned in the laboratory page and retrieves more information about the country
     *
     * @param urlStr
     * @param code
     * @return
     */
    private String httpRequest(String urlStr, String code) {
        HttpResponse response;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet getConnection = new HttpGet(urlStr + code);
        try {
            response = httpClient.execute(getConnection);
            return EntityUtils.toString(response.getEntity(),
                    "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    /**
     * Returns the message associated with "info" command
     *
     * @param locale
     */
    private void infoLocaleCommand(Locale locale) {
        String baseName = "Messages";
        ResourceBundle messages =
                ResourceBundle.getBundle(baseName, locale);
        String formatted = new MessageFormat(messages.getString("info")).format(new Object[]{locale.getCountry()});
        System.out.println(formatted);
    }
}
