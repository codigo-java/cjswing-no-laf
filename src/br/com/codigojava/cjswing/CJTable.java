package br.com.codigojava.cjswing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class CJTable extends JTable{

	private static final long serialVersionUID = 1L;
	
	static ColorUIResource backgroundColor  = new ColorUIResource(new Color(255, 229, 204));
	static ColorUIResource primaryColor  = new ColorUIResource(new Color(255, 255, 250));
	static ColorUIResource secondaryColor  = new ColorUIResource(new Color(245, 245, 245));
    
	public CJTable(){
		super();
		setRowHeight(20);
		setAutoCreateRowSorter(false);
		setIntercellSpacing(new Dimension(0, 0));
   		setGridColor(primaryColor); 	
   		
   		JTableHeader header = this.getTableHeader();
   		header.setDefaultRenderer(new HeaderRenderer(this));
   	    //header.setForeground(Color.RED);
   	    header.setFont(this.getFont().deriveFont(Font.BOLD));
   	    //header.setAutoscrolls(true);
   	    //header.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
	}
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) { 
    	Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);   
       	c.setBackground(rowIndex % 2 == 0 ? primaryColor : secondaryColor);  
   		c.setForeground(Color.BLACK);
       	((JComponent) c).setBorder(BorderFactory.createLineBorder(primaryColor, 1));//(Color.WHITE));
       	if(super.isRowSelected(rowIndex)){
       		c.setBackground(backgroundColor);
       		c.setForeground(new Color(165, 42, 42));
       		c.setFont(this.getFont().deriveFont(Font.BOLD));
       	}
        return c; 
	}
}
