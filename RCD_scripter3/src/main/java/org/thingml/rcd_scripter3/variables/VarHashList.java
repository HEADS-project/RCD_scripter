/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3.variables;

import java.util.ArrayList;
import java.util.Iterator;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ExecuteException;

/**
 *
 * @author steffend
 */
public class VarHashList extends VarBase {

    public ArrayList<VarHash> hashList = null;
    private VarHash defaultHash = null;

    public VarHashList() {
        hashList = new ArrayList<VarHash>();
        defaultHash = new VarHash();
    }

    public VarHashList(VarHashList copyFromList) {
        hashList = new ArrayList<VarHash>();
        defaultHash = new VarHash();
        copyList(copyFromList); 
    }

    private void copyList(VarHashList copyFromList) {
        if (copyFromList != null) {
            defaultHash = new VarHash(copyFromList.defaultHash);
                
            Iterator i1 = copyFromList.hashList.iterator();
            int n1 = 0;
            while(i1.hasNext()) {
                VarHash  row1  = (VarHash)i1.next();

                VarHash newHash = new VarHash(row1);
                hashList.add(newHash);
                n1++;
            }
        }
    }
    
    public VarHash getDefaultHash() {
        return defaultHash;
    }
    
    public void setDefaultHash(VarHash def_hash) {
        defaultHash = def_hash;
    }
    
    
    public void addHash(VarHash newHash) {
        
        // Assure that all default cells not defined in new row are included
        VarHash tmpHash = new VarHash(defaultHash); // Start with default cells
        tmpHash.addHash(newHash);  // Add the new cells
        
        hashList.add(tmpHash);
    }

    @Override
    public String printString(){
        String ret = "<VarHashList: Default hash: "+defaultHash.printString()+"\n";
        ret += getString();
        return ret;
    }

    
    @Override
    public String getString(){
        String ret = "";
        Iterator i = hashList.iterator();
        int n = 0;
        while(i.hasNext()) {
            ret += "Row #" + n + ":"+((VarHash)(i.next())).printString()+"\n";
            n++;
        }

        ret += ">\n";
        return ret;
    }

    @Override
    public String getTypeString() {
        return "VarHashList";
    }

    @Override
    public VarType getType() {
        return VarType.HASHLIST;
    }
    
}
