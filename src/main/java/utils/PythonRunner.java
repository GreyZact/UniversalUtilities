/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 * Utility for executing
 *
 * @author mike
 */
public class PythonRunner {

    public void exec(String script) {
        
    }
    
    public void runFromFile(String file) {
        FileExplorer fileExplorer = new FileExplorer();
        String script = fileExplorer.openFile(file);
        exec(script);
    }
    
}
