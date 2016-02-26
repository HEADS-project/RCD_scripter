/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.variables;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.thingml.rcd_scripter2.ExecuteContext;

/**
 *
 * @author steffend
 */
public class VarFile extends VarBase {
 
    private String name;
    private BufferedWriter bufferedWriter;
    private boolean isOpen = false;
    
    public VarFile(String name) {
        this.name = name;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(this.name));
        } catch (IOException ex) {
            Logger.getLogger(VarFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        isOpen = true;
    }

    public void close() {
        try {
            bufferedWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(VarFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        isOpen = false;
    }
    
    public void write(ExecuteContext ctx, String txt) {
        if (isOpen) {
            try {
                bufferedWriter.write(txt);
            } catch (IOException ex) {
                Logger.getLogger(VarFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("ERROR write to closed file <"+name+">");
            ctx.printExecutingInfo();
        }
    }
    
    @Override
    public String printString(){
        String ret = "<"+getTypeString()+":"+name+">\n";
        return ret;
    }

    @Override
    public String getTypeString() {
        return this.getClass().getSimpleName();
    }
    
}
