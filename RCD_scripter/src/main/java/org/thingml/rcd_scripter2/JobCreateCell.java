/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2;

/**
 *
 * @author steffend
 */
public class JobCreateCell extends JobCreateCellBase {
    private String id;
    private String image;
    private CellType cellType;
    public enum CellType {ID, INT, STRING};
    

    public JobCreateCell(CellType cellType, String id, String image) {
        this.cellType = cellType;
        this.id = id;
        this.image = image;
    }
    
    public VarCellBase execute(ExecuteContext ctx) {
        VarCellBase newCell = null;
        switch(cellType) {
            case ID:
                newCell = new VarCellId();
                newCell.setId(id);
                newCell.setImage(image);
                break;
            case INT:
                newCell = new VarCellInt();
                newCell.setId(id);
                newCell.setImage(image);
                break;
            case STRING:
                newCell = new VarCellString();
                newCell.setId(id);
                newCell.setImage(image);
                break;
        }
        return newCell;
    }
}
