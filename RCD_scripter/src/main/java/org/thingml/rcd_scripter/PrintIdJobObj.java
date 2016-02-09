/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter;

/**
 *
 * @author steffend
 */
public class PrintIdJobObj extends JobObj {
    
    @Override
    public String getType() {
        return "Id";
    }
    
    @Override
    public void execute(RowObj row) {
        CellObj cell = row.getCell(image);
        System.out.print(cell.getImage());
    }
}
