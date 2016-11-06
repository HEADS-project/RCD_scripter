/**
 * Copyright (C) 2016 SINTEF <steffen.dalgard@sintef.no>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    public ExecResult execute(ExecuteContext ctx) throws ExecuteException {
        String image = getName();

        image = image.substring(0, image.length()-1).substring(1);
        image = image.replace("\\r", CR);
        image = image.replace("\\n", NL);
        image = image.replace("\\t", TAB);
        image = image.replace("\\\"", QUOTE);
        
        ctx.pushVar(new VarValueString(image));
        return ExecResult.NORMAL;
    }


}
