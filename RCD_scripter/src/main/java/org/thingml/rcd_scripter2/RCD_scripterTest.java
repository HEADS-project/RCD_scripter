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
        
        
        //String inputfile = "C:/javasrc/RCD_scripter/RCD_scripter/src/main/java/org/thingml/rcd_scripter2/app_msgdef2-vartest.h";
        String inputfile = "RCD_script.h";
        
        if (args.length > 0) {
            inputfile = args[0];
        }
        
        System.out.println("RCD scripter START");
        try{
            //jobContext.setTrace(true);
            
            System.out.println("Parser init using file <"+inputfile+">");
            System.out.println("<");
            parser = new RcdScript2Parser(new FileReader(inputfile)); 
            System.out.println(">");

            System.out.println("Parse file and generate jobs...");
            System.out.println("<");
            jobList = parser.makeJobs(); 
            System.out.println(">");
            
            System.out.println("Executing jobs...");
            System.out.println("<");
            jobList.executeList(jobContext);
            System.out.println(">");
            //System.out.println(jobContext.printStringAll());
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        System.out.println("RCD scripter END");
    }
}