/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3.variables;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ExecuteException;

/**
 *
 * @author steffend
 */
public class VarFile extends VarBase {
 
    private String name = "";
    private BufferedWriter bufferedWriter = null;
    private boolean isOpen = false;
    
    public VarFile() {
    }

    public void open(ASTRcdBase b, String name) throws ExecuteException {
        this.name = name;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(this.name));
        } catch (IOException ex) {
            b.generateExecuteException("ERROR opening file <"+name+">\n"+ex);
        }
        isOpen = true;
    }

    public void close(ASTRcdBase b) throws ExecuteException {
        try {
            bufferedWriter.close();
        } catch (IOException ex) {
            b.generateExecuteException("ERROR closing file <"+name+">\n"+ex);
        }
        isOpen = false;
    }
    
    public void write(ASTRcdBase b, String txt) throws ExecuteException {
        if (isOpen) {
            try {
                bufferedWriter.write(txt);
            } catch (IOException ex) {
                b.generateExecuteException("ERROR writing to file <"+name+">\n"+ex);
            }
        } else {
            b.generateExecuteException("ERROR writing to closed file <"+name+">\n");
        }
    }
    
    @Override
    public String getTypeString() {
        return "File";
    }

    @Override
    public VarType getType() {
        return VarType.FILE;
    }

    @Override
    public String printString() {
        String ret = "File: " + getString();
        return ret;
    }
    
    @Override
    public String getString() {
        return name;
    }

}
