/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2;

import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author steffend
 */
/*for testing the parser class*/ 
public class RCD_scripterTest { 
    public static void main(String[] args) { 
        RcdScript2Parser parser;
        JobList jobList;
        ExecuteContext jobContext = new ExecuteContext();
        
        //String inputfile = "C:\\javasrc\\javacc_examples\\src\\main\\java\\no\\sintef\\instrumentation\\javacc_example\\sql_script.txt";
        //String inputfile = "C:/javasrc/RCD_scripter/RCD_scripter/src/main/java/org/thingml/rcd_scripter/app_msgdef4_red.h";
        String inputfile = "C:/javasrc/RCD_scripter/RCD_scripter/src/main/java/org/thingml/rcd_scripter2/app_msgdef2_1.h";
        System.out.println("Parser starting...");
        try{
            parser = new RcdScript2Parser(new FileReader(inputfile)); 

            jobList = parser.makeJobs(); 
            jobList.execute(jobContext);
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
}