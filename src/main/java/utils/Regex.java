/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Much needed simple regex tool for Java
 *
 * @author mike
 */
public class Regex {

    private final Debug debug;

    /**
     * Constructor
     *
     */
    public Regex() {
        this.debug = new Debug("Regex: ");
    }

    /**
     * Getter for Debug
     *
     * @return debug message prefix
     */
    public Debug getDebug() {
        return debug;
    }

    /**
     * Setter for toggling debugging and writing log to file ON/OFF.
     *
     * @param isOn parameter which decides if class writes messages
     * @param writeToFile parameter which decides if class writes messages to
     * additional file
     */
    public void setDebugging(Boolean isOn, Boolean writeToFile) {
        this.debug.setOn(isOn);
        this.debug.setWriteToFile(writeToFile);
    }

    /**
     * Used for finding String[] fulfilling set regex rules.
     *
     * @param input String in which we want to find something
     * @param regex String containing regex rules to find
     * @return Returns String[] groups fulfilling set regex rules
     */
    public List<String[]> findAll(String input, String regex) {
        LinkedList<String[]> tmpList = new LinkedList<String[]>();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            int groupCount = matcher.groupCount();
            String[] tmpStringArray = new String[groupCount];
            for (int i = 0; i < groupCount; i++) {
                debug.log("matcher.group(" + i + "): " + matcher.group(i));
                tmpStringArray[i] = matcher.group(i);
            }
            tmpList.add(tmpStringArray);
        }
        return tmpList;
    }

    /**
     * Used for finding FIRST String fulfilling set regex rules.
     *
     * @param input String in which we want to find something
     * @param regex String containing regex rules to find
     * @return Returns FIRSRT String fulfilling set regex rules
     */
    public String find(String input, String regex) {
        return findAll(input, regex).get(0)[0];
    }

    /**
     * Used for checking if input contains provided regex.
     *
     * @param input String in which we want to find something
     * @param regex String containing regex rules to find
     * @return Returns true if found
     */
    public Boolean contains(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
