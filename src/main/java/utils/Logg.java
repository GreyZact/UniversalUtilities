/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Wrapper for Logger
 * <p>
 * You can only have ONE instance of this class for retaining app.log file
 * integrity.
 *
 * @author mike
 */
public class Logg {

    private static final Logg instance;
    private static final String filePath = "app.log";
    private static boolean writeToFile = true;
    private static final FileExplorer fileExplorer;
    private static int logLevel = 0;

    /**
     * A private Constructor prevents any other class from instantiating.
     */
    private Logg() {
        // nothing to do this time
    }

    /**
     * The Static initializer constructs the instance at class loading time;
     * this is to simulate a more involved construction process (it it were
     * really simple, you'd just use an initializer)
     */
    static {
        instance = new Logg();
        fileExplorer = new FileExplorer();
    }

    /**
     * Static 'instance' method
     *
     * @return instance
     */
    public static Logg getInstance() {
        return instance;
    }

    /**
     * For checking if writing messages to file (app.log) is on or off.
     * <p>
     * [true - ON ]
     * <p>
     * [false - OFF]
     *
     * @return returns if writing messages to file is on (true) or off (false)
     */
    public static boolean isWriteToFile() {
        return writeToFile;
    }

    /**
     * Used to turn writing to file on/off.
     * <p>
     * [default: writeToFile = false]
     *
     * @param writeToFile parameter which decides if class writes messages to
     * additional file
     */
    public static void setWriteToFile(boolean writeToFile) {
        Logg.writeToFile = writeToFile;
    }

    /**
     * For checking logging level.
     *
     * @return returns level of logging as Integer
     */
    public static Integer getLogLevel() {
        return logLevel;
    }

    /**
     * For setting logging level.
     *
     * @param logLevel level of logging as Integer
     */
    public static void setLogLevel(int logLevel) {
        Logg.logLevel = logLevel;
    }

    /**
     * For logging message as Level.ALL.
     *
     * @param message Message to print in console and add to log file
     */
    public static void printALL(String message) {
        print(message, Level.ALL);
    }

    /**
     * For logging message as Level.CONFIG.
     *
     * @param message Message to print in console and add to log file
     */
    public static void printCONFIG(String message) {
        print(message, Level.CONFIG);
    }

    /**
     * For logging message as Level.FINE.
     *
     * @param message Message to print in console and add to log file
     */
    public static void printFINE(String message) {
        print(message, Level.FINE);
    }

    /**
     * For logging message as Level.FINEST.
     *
     * @param message Message to print in console and add to log file
     */
    public static void printFINEST(String message) {
        print(message, Level.FINEST);
    }

    /**
     * For logging message as Level.INFO.
     *
     * @param message Message to print in console and add to log file
     */
    public static void printINFO(String message) {
        print(message, Level.INFO);
    }

    /**
     * For logging message as Level.OFF.
     *
     * @param message Message to print in console and add to log file
     */
    public static void printOFF(String message) {
        print(message, Level.OFF);
    }

    /**
     * For logging message as Level.SEVERE.
     *
     * @param message Message to print in console and add to log file
     */
    public static void printSEVERE(String message) {
        print(message, Level.SEVERE);
    }

    /**
     * For logging message as Level.WARNING.
     *
     * @param message Message to print in console and add to log file
     */
    public static void printWARNING(String message) {
        print(message, Level.WARNING);
    }

    /**
     * Universal method for logging message.
     *
     * @param message Message to print in console and add to log file
     * @param level For setting logging Level
     */
    public static void print(String message, Level level) {
        // Log only if Level is high enought
        if (logLevel < level.intValue()) {
            //  Log to console
            Logger.getLogger(FileExplorer.class.getName()).log(level, message);
            //  Save to file
            if (writeToFile) {
                fileExplorer.push(filePath, getTime() + "[" + level.toString() + "] " + message);
            }
        }
    }

    /**
     * Used for getting time in format "yyyy-MM-dd' T'HH:mm:ss.SSSXXX".
     *
     */
    private static String getTime() {
        return String.valueOf(new SimpleDateFormat("yyyy-MM-dd' T'HH:mm:ss.SSSXXX").format(Calendar.getInstance().getTime())) + " > ";
    }
}
