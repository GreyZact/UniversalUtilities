/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Reads and makes available options saved in "config.file".
 * <p>
 * Keys in configuration files must start from symbols fulfilling conditions
 * "[a-zA-Z]+" ,rest is treated as comments.
 * <p>
 * WARNING: Kewys are case INSENSITIVE.
 * <p>
 * You can only have ONE instance of this class for retaining Configuration file
 * integrity.
 *
 * @author mike
 */
public class Config {

    private static Config instance;
    private static HashMap<String, String> settingsMap;
    private static final String configFile = "config.file";
    private static Regex regex = new Regex();

    /**
     * A private Constructor prevents any other class from instantiating.
     */
    private Config() {
        // nothing to do this time
    }

    /**
     * The Static initializer constructs the instance at class loading time;
     * this is to simulate a more involved construction process (it it were
     * really simple, you'd just use an initializer)
     */
    static {
        instance = new Config();
        settingsMap = load();
    }

    /**
     * Static 'instance' method
     *
     * @return instance
     */
    public static Config getInstance() {
        return instance;
    }

    /**
     * Used for getting LinkedList containing names of all Keys of settings in
     * config.file
     *
     * @return LinkedList containing names (String) of all Keys
     */
    public static LinkedList<String> getKeyList() {
        return new LinkedList<>(settingsMap.keySet());
    }

    /**
     * Used for getting values contained as String under provided Key.
     *
     * @param key Name (String) of Key
     * @return Returns value (String) contained under provided Key (String)
     */
    public static String getKey(String key) {
        return settingsMap.get(key.toLowerCase());
    }

    private static HashMap<String, String> load() {
        HashMap<String, String> tmpMap = new HashMap<>();
        // Read config file
        LinkedList<String> tmpList = new FileExplorer().read(configFile);
        // Convert config file to HashMap
        tmpList.stream().map((item) -> item.split("=")).forEachOrdered((tmpArray) -> {
            if (regex.contains(String.valueOf(tmpArray[0].charAt(0)), "[a-zA-Z]+")) {
                // Trim
                tmpArray[0] = tmpArray[0].trim();
                tmpArray[0] = tmpArray[0].toLowerCase();
                tmpArray[1] = tmpArray[1].trim();
                tmpMap.put(tmpArray[0], tmpArray[1]);
            }
        });
        return tmpMap;
    }
}
