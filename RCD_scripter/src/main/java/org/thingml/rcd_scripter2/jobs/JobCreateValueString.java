/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarValueBase;
import org.thingml.rcd_scripter2.variables.VarValueString;

/**
 *
 * @author steffend
 */
public class JobCreateValueString extends JobBase_VarValueBase {
    private String image;
    private final String CR = "\r";
    private final String NL = "\n";
    private final String TAB = "\t";
    private final String QUOTE = "\"";
    

    public JobCreateValueString(Token t, String image, boolean removeQuotes) {
        super(t);
        if (removeQuotes == true) {
            this.image = image.substring(0, image.length()-1).substring(1);
        } else {
            this.image = image;
        }
        //this.image = this.image.replace("\r\n", NL);
        //this.image = this.image.replace("\n\r", NL);
        //this.image = this.image.replace("\r", NL);
        this.image = this.image.replace("\\r", CR);
        this.image = this.image.replace("\\n", NL);
        this.image = this.image.replace("\\t", TAB);
        this.image = this.image.replace("\\\"", QUOTE);
    }
    
    @Override
    public String getTypeString() {
        return "JobCreateValueString";
    }
    
    @Override
    protected VarValueBase executeInternal(ExecuteContext ctx) {
        VarValueBase ret = new VarValueString(image);
        return ret;
    }
}
