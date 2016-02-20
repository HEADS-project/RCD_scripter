/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Old;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.jobs.JobBase_Obj;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarCell;

/**
 *
 * @author steffend
 */
abstract public class JobBase_String extends JobBase_Obj {
 
    public JobBase_String(Token t) {
        super(t);
    }
    
    abstract public String getTypeString();
    abstract public String execute(ExecuteContext ctx);

}
