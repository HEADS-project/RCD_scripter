/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter;

import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author steffend
 */
/*for testing the parser class*/ 
public class RCD_scripterTest { 
  public static void main(String[] args) { 
    RcdScriptParser parser;
    //String inputfile = "C:\\javasrc\\javacc_examples\\src\\main\\java\\no\\sintef\\instrumentation\\javacc_example\\sql_script.txt";
    String inputfile = "C:/javasrc/RCD_scripter/RCD_scripter/src/main/java/org/thingml/rcd_scripter/app_msgdef4_red.h";
    System.out.println("Parser starting...");
    try{
        parser = new RcdScriptParser(new FileReader(inputfile)); 
   
        TableList tableList = parser.executeScript(); 
        tableList.print();
    } 
   
    catch (Exception ex) 
    {ex.printStackTrace() ;}}
}