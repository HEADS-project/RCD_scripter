/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3.proc;

import java.util.List;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.variables.VarBase;

/**
 *
 * @author steffend
 */
public interface ProcBaseIf {
    VarBase executeProc(ExecuteContext ctx, ASTRcdBase callersBase, List<VarBase> args) throws ExecuteException;
}
