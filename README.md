# -PA-LAB13

This is the compulsory and the optional part of the 13th laboratory.

For this laboratory we had to create an application to explore the available locales included in the standard Java Development Kit.

In the *res* package are the files: **Messages.properties** and **Messages_ro.properties**.<br/>
In the *com* package I implemented the classes that describe the commands:
- **DisplayLocales**: displays all available locales
- **SetLocale**: sets the application current locale
- **Info**: displays informations about the current or a specific locale

In the *app* package there is the main class **LocaleExplore**,that reads commands from the keyboard and executes them.

Using the external file **Commands.properties** the command names are mapped to the corresponding classes.
