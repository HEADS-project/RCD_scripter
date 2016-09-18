/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3.variables;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.parser.Token;

/**
 *
 * @author steffend
 */
abstract public class VarBase {
    public enum VarType { KEYVALUE, HASHLIST, ID, HASH, INT, STRING, FILE, VALARRAY };

    abstract public String getString();
    abstract public String printString();
    abstract public String getTypeString();
    abstract public VarType getType();
    
    public VarBase fetchFromIndex(ASTRcdBase b, VarBase var) throws ExecuteException {
        throw b.generateExecuteException("ERROR indexing not supported for "+getTypeString());
    }
    
}
