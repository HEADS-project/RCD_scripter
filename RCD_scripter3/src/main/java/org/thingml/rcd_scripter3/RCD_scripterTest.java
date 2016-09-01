/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3;
import org.thingml.rcd_scripter3.parser.*;
import java.io.FileReader;

/**
 *
 * @author steffend
 */
/*for testing the parser class*/ 
public class RCD_scripterTest { 
    public static void main(String[] args) { 
        RcdScript3Parser parser;
        ASTStart ast;
        
        
        String inputfile = "C:/javasrc/RCD_scripter/RCD_scripter3/src/main/java/org/thingml/rcd_scripter3/app_msgdef3-vartest.h";
        //String inputfile = "RCD_script.h";
        
        if (args.length > 0) {
            inputfile = args[0];
        }
        
        System.out.println("RCD scripter START");
        try{
            //jobContext.setTrace(true);
            
            System.out.println("Parser init using file <"+inputfile+">");
            System.out.println("<");
            parser = new RcdScript3Parser(new FileReader(inputfile)); 
            System.out.println(">");

            System.out.println("Parse file and generate AST...");
            System.out.println("<");
            ast = parser.Start(); 
            System.out.println(">");
          
            ast.dump("");
  
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        System.out.println("RCD scripter END");
    }
}