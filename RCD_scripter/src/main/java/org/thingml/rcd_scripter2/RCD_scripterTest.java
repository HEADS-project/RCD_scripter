/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2;
import org.thingml.rcd_scripter2.jobs.JobList_Obj;
import org.thingml.rcd_scripter2.parser.RcdScript2Parser;
import java.io.FileReader;

/**
 *
 * @author steffend
 */
/*for testing the parser class*/ 
public class RCD_scripterTest { 
    public static void main(String[] args) { 
        RcdScript2Parser parser;
        JobList_Obj jobList;
        ExecuteContext jobContext = new ExecuteContext();
        
        
        //String inputfile = "C:/javasrc/RCD_scripter/RCD_scripter/src/main/java/org/thingml/rcd_scripter2/app_msgdef2-1.h";
        String inputfile = "C:/javasrc/RCD_scripter/RCD_scripter/src/main/java/org/thingml/rcd_scripter2/app_msgdef2-vartest.h";
        
        if (args.length > 0) {
            inputfile = args[0];
        }
        
        System.out.println("Using file <"+inputfile+">");
        System.out.println("Parser starting...");
        try{
            //jobContext.setTrace(true);
            
            parser = new RcdScript2Parser(new FileReader(inputfile)); 

            jobList = parser.makeJobs(); 
            jobList.executeList(jobContext);
            //System.out.println(jobContext.printStringAll());
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
}