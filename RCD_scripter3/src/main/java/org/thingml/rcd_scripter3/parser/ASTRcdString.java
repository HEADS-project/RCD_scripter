
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarValueString;

public class ASTRcdString extends ASTRcdBase {
    private final String CR = "\r";
    private final String NL = "\n";
    private final String TAB = "\t";
    private final String QUOTE = "\"";

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdString(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        String image = getName();

        image = image.substring(0, image.length()-1).substring(1);
        image = image.replace("\\r", CR);
        image = image.replace("\\n", NL);
        image = image.replace("\\t", TAB);
        image = image.replace("\\\"", QUOTE);
        
        ctx.pushVar(new VarValueString(image));
    }


}
