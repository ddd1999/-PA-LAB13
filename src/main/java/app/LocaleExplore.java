package app;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    /**
     * Reading commands and executing them
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Locale locale = Locale.getDefault();
        do {
            displayPromptMessage(locale);
            String s = scanner.nextLine();
            locale = runCommand(s, locale);
        } while (true);
    }

    /**
     * Runs the command identified by the string s for Locale locale
     *
     * @param s
     * @param locale
     * @return
     */
    private static Locale runCommand(String s, Locale locale) {
        java.lang.reflect.Method method;
        if (s.equals(getCommandName("display-locale"))) {
            String implementation = getCommandImplementation("display-locale");
            try {
                method = Class.forName(implementation).newInstance().getClass().getMethod("cmd", Locale.class);
                method.invoke(Class.forName(implementation).newInstance(), locale);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (s.equals(getCommandName("set-locale"))) {
            String implementation = getCommandImplementation("set-locale");
            try {
                method = Class.forName(implementation).newInstance().getClass().getMethod("cmd", Locale.class);
                locale = (Locale) method.invoke(Class.forName(implementation).newInstance(), locale);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (s.equals(getCommandName("info-locale"))) {
            String implementation = getCommandImplementation("info-locale");
            try {
                method = Class.forName(implementation).newInstance().getClass().getMethod("cmd", Locale.class);
                method.invoke(Class.forName(implementation).newInstance(), locale);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            displayErrorMessage(locale);
        }
        return locale;
    }

    /**
     * Returns the string that the user has to type in order to execute the command
     *
     * @param command
     * @return
     */
    private static String getCommandName(String command) {
        Properties prop = new Properties();
        InputStream stream = LocaleExplore.class.getResourceAsStream("/commands.properties");
        try {
            prop.load(stream);
            String str = prop.getProperty(command + ".command");
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * Returns the name of the class where the command is implemented
     *
     * @param command
     * @return
     */
    private static String getCommandImplementation(String command) {
        Properties prop = new Properties();
        InputStream stream = LocaleExplore.class.getResourceAsStream("/commands.properties");
        try {
            prop.load(stream);
            String str = prop.getProperty(command + ".impl");
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * Returns the prompt message for current locale
     *
     * @param locale
     */
    private static void displayPromptMessage(Locale locale) {
        String baseName = "Messages";
        ResourceBundle messages =
                ResourceBundle.getBundle(baseName, locale);
        System.out.print(messages.getString("prompt"));
    }

    /**
     * Returns the error message for current locale
     *
     * @param locale
     */
    private static void displayErrorMessage(Locale locale) {
        String baseName = "Messages";
        ResourceBundle messages =
                ResourceBundle.getBundle(baseName, locale);
        System.out.println(messages.getString("invalid"));
    }
}
