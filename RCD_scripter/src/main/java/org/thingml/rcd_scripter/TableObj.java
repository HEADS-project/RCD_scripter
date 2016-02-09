/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author steffend
 */
class TableObj {

    private ArrayList<RowObj> rowList = null;
    private RowObj defaultRow = null;

    public TableObj() {
        rowList = new ArrayList<RowObj>();
        defaultRow = new RowObj();
    }

    public RowObj getDefaultRowObj() {
        return defaultRow;
    }
    
    public RowObj getNewRowObj() {
        RowObj newRow = defaultRow.makeCopy();
        rowList.add(newRow);
        return newRow;
    }
    
    public void print(){
        System.out.print("Default row entries : ");
        defaultRow.print();
        Iterator i = rowList.iterator();
        int n = 0;
        while(i.hasNext()) {
            System.out.print("Row #" + n + " entries :");
            ((RowObj)(i.next())).print();
            n++;
        }

    }

    void join(TableObj orgtab1, String orgcol1_name, TableObj orgtab2, String orgcol2_name) {
        Iterator i1 = orgtab1.rowList.iterator();
        int n1 = 0;
        while(i1.hasNext()) {
            RowObj  row1  = (RowObj)i1.next();
            CellObj cell1 = row1.getCell(orgcol1_name);
            
            Iterator i2 = orgtab2.rowList.iterator();
            int n2 = 0;
            while(i2.hasNext()) {
                RowObj  row2  = (RowObj)i2.next();
                CellObj cell2 = row2.getCell(orgcol2_name);

                if( cell1 != null) {
                    if( cell2 != null) {
                        if( cell1.compareTypeAndVal(cell2) == true) {
                            RowObj newRow = new RowObj();
                            rowList.add(newRow);
                            newRow.addRow(row1.makeCopy());
                            newRow.addRow(row2.makeCopy());
                        }
                    }
                }
                n2++;
            }
            n1++;
        }
    }

    void copyRowsIfEq(TableObj orgtab, CellObj match_cell) {
        Iterator i1 = orgtab.rowList.iterator();
        int n1 = 0;
        while(i1.hasNext()) {
            RowObj  row1  = (RowObj)i1.next();
            CellObj cell  = row1.getCell(match_cell.getId());

            if( cell != null) {
                if( cell.compareTypeAndVal(match_cell) == true) {
                    RowObj newRow = new RowObj();
                    rowList.add(newRow);
                    newRow.addRow(row1.makeCopy());
                }
            }
            n1++;
        }
    }

    void createColumnsConcat(String newcol_name, String col1_name, String col2_name) {
        Iterator i1 = rowList.iterator();
        int n1 = 0;
        while(i1.hasNext()) {
            RowObj  row1  = (RowObj)i1.next();
            CellObj cell1 = row1.getCell(col1_name);
            CellObj cell2 = row1.getCell(col2_name);
            String  new_image = "";
            CellObj new_cell = new IdCellObj();
            
            if( cell1 != null) {
                if( cell1 instanceof StringCellObj) {
                    new_cell = new StringCellObj();
                }
                new_image += cell1.getImage();
            }
            if( cell2 != null) {
                if( cell2 instanceof StringCellObj) {
                    new_cell = new StringCellObj();
                }
                new_image += cell2.getImage();
            }

            new_cell.setId(newcol_name);
            new_cell.setImage(new_image);
            row1.addCell(new_cell);
            
            n1++;
        }
    }
}
