package utils;


import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import utils.Debug;
import utils.FileExplorer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mike
 */
public class FileExplorerTest {

    Debug debug;
    LinkedList exampleList = new LinkedList();
    String exampleStr;
    String FILENAME = "test.txt";
    FileExplorer fileExplorer = new FileExplorer();

    @Before
    public void BeforeTest() {
        debug = new Debug("FileExplorerTest: ");
//        Debugging
//        debug.setOn(true);
//        fileExplorer.setDebugging(Boolean.TRUE, Boolean.FALSE);
        populateString();
        populateList();
        fileExplorer.write(FILENAME, exampleStr);
    }

    @Test
    public void openTest() {
        String tmpString = fileExplorer.openFile(FILENAME);
        debug.log(tmpString);
        assertEquals(exampleStr, tmpString);
    }

    @Test
    public void readTest() {
        LinkedList<String> tmpList = fileExplorer.read(FILENAME);
        assertEquals(exampleList, tmpList);
    }

    @Test
    public void lsTest() {
        Boolean flag = false;
        List<String> lsList = fileExplorer.ls("");
        for (String s : lsList) {
            if (s.equals(FILENAME)) {
                flag = true;
            }
        }
        assertTrue(flag);
    }
    private void populateString(){
        StringBuilder tmpBuilder = new StringBuilder();
        tmpBuilder.append("lorem");
        tmpBuilder.append(System.getProperty("line.separator"));
        tmpBuilder.append("ipsum");
        tmpBuilder.append(System.getProperty("line.separator"));
        
        exampleStr = tmpBuilder.toString();
    }
    private void populateList() {
        String[] tmpArr = exampleStr.split("\n");
        for (String s : tmpArr) {
            exampleList.add(s);
        }
    }
}
