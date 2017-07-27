/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Utility used for reading and writing files.
 *
 * @author mike
 */
public class FileExplorer {

    private final Debug debug = new Debug("FileExplorer: ");
    private String PATH = System.getProperty("user.dir") + "/../";

    /**
     * Constructor
     *
     */
    public FileExplorer() {
    }

    /**
     * Getter of base file directory path.
     *
     * @return base file directory path
     */
    public String getPATH() {
        return PATH;
    }

    /**
     * Setter for changing base file directory path.
     *
     * @param PATH change base file directory path
     */
    public void setPATH(String PATH) {
        this.PATH = PATH;
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
     * Used for writing (String) data to output file.
     *
     * @param output output file
     * @param data String to save in file
     */
    public void write(String output, String data) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            fw = new FileWriter(PATH + output);
            bw = new BufferedWriter(fw);
            bw.write(data);

            debug.log("write: " + PATH + output + " : WRITEN");

        } catch (IOException ex) {
            debug.log("write: " + PATH + output + " : ERROR");
            Logger.getLogger(FileExplorer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {

                if (bw != null) {
                    bw.close();

                }

                if (fw != null) {
                    fw.close();
                }

            } catch (IOException ex) {
                debug.log("write: " + PATH + output + " : ERROR");
                Logger.getLogger(FileExplorer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Used for writing binary image contents to output file.
     * <p>
     * Warning: output file needs extension (.jpg|.png|.gif|etc.).
     *
     * @param output output file
     * @param rawImage binary image contents to save in file
     */
    public void writeImage(String output, BufferedImage rawImage) {
        // Get filetype from output parametr
        String[] tmpStringArray = output.split("\\.");
        String filetype = tmpStringArray[tmpStringArray.length - 1];

        try {
            BufferedImage bi = rawImage;
            File outputfile = new File(output);
            //  Write raw image data to file
            ImageIO.write(bi, filetype, outputfile);
        } catch (IOException ex) {
            debug.log("writeImage: " + PATH + output + " : ERROR");
            Logger.getLogger(FileExplorer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Used for adding new line (String) newLine at the end of output (text)
     * file.
     *
     * @param output output file
     * @param newLine String to add at the end of file
     */
    public void push(String output, String newLine) {
        try {
            FileOutputStream fop = new FileOutputStream(PATH + output, true);
            PrintStream ps = new PrintStream(fop);

            //  Add newLine String to new line at the end of file.
            ps.println(newLine);
            ps.close();
            debug.log("push: " + PATH + output + " : WRITEN");
        } catch (FileNotFoundException ex) {
            debug.log("push: " + PATH + output + " : ERROR");
            Logger.getLogger(FileExplorer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Used for reading text files line by line and returning it as LinkedList
     * (retains order).
     *
     * @param input file to open
     * @return LinkedList containing lines of text file
     */
    public LinkedList<String> read(String input) {
        LinkedList<String> tmpLinkedList = new LinkedList<String>();

        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(PATH + input);
            br = new BufferedReader(fr);

            String sCurrentLine;

            //  Populate list line by line.
            while ((sCurrentLine = br.readLine()) != null) {
                tmpLinkedList.add(sCurrentLine);
            }
        } catch (FileNotFoundException ex) {
            debug.log("read: " + PATH + input + " : ERROR");
            Logger.getLogger(FileExplorer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            debug.log("read: " + PATH + input + " : ERROR");
            Logger.getLogger(FileExplorer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                debug.log("read: " + PATH + input + " : ERROR");
                Logger.getLogger(FileExplorer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        debug.log("read: " + PATH + input + " : READ");
        return tmpLinkedList;
    }

    /**
     * Used for reading text files as one String.
     *
     * @param input file to open
     * @return Full text file contents as String
     */
    public String openFile(String input) {
        StringBuilder tmpString = new StringBuilder();
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(PATH + input);
            br = new BufferedReader(fr);

            String sCurrentLine;

            //  Add line by line to String.
            
            while ((sCurrentLine = br.readLine()) != null) {
                tmpString.append(sCurrentLine);
                tmpString.append(System.getProperty("line.separator"));
            }

        } catch (FileNotFoundException ex) {
            debug.log("openFile: " + PATH + input + " : ERROR");
            Logger.getLogger(FileExplorer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            debug.log("openFile: " + PATH + input + " : ERROR");
            Logger.getLogger(FileExplorer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {
                debug.log("openFile: " + PATH + input + " : ERROR");
                Logger.getLogger(FileExplorer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        debug.log("openFile: " + PATH + input + " : READ");
        return tmpString.toString();
    }

    /**
     * Used for returning list of all FILES and FOLDERS in directory.
     *
     * @param directory path to folder
     * @return LinkedList containing names (String) of files and folders in
     * directory.
     */
    public LinkedList<String> ls(String directory) {
        LinkedList<String> tmpList = new LinkedList<>();
        File folder = new File(PATH + directory);
        File[] listOfFiles = folder.listFiles();

        for (File item : listOfFiles) {
            tmpList.add(item.getName());
        }

//        for (int i = 0; i < listOfFiles.length; i++) {
//            if (listOfFiles[i].isFile()) {
//                System.out.println("File " + listOfFiles[i].getName());
//            } else if (listOfFiles[i].isDirectory()) {
//                System.out.println("Directory " + listOfFiles[i].getName());
//            }
//        }
        return tmpList;
    }

    /**
     * Used for returning list of ONLY all FILES in directory.
     *
     * @param directory path to folder
     * @return LinkedList containing names (String) of files in directory.
     */
    public LinkedList<String> lsFiles(String directory) {
        LinkedList<String> tmpList = new LinkedList<>();
        File folder = new File(PATH + directory);
        File[] listOfFiles = folder.listFiles();

        for (File item : listOfFiles) {
            if (item.isFile()) {
                tmpList.add(item.getName());
            }
        }
        return tmpList;
    }

    /**
     * Used for returning list of ONLY all FOLDERS in directory.
     *
     * @param directory path to folder
     * @return LinkedList containing names (String) of folders in directory.
     */
    public LinkedList<String> lsDirs(String directory) {
        LinkedList<String> tmpList = new LinkedList<>();
        File folder = new File(PATH + directory);
        File[] listOfFiles = folder.listFiles();

        for (File item : listOfFiles) {
            if (item.isDirectory()) {
                tmpList.add(item.getName());
            }
        }
        return tmpList;
    }
}
