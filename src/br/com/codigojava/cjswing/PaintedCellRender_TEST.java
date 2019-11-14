package br.com.codigojava.cjswing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class PaintedCellRender_TEST extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 3146102832659403723L;
	static String msg;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, 
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);

		table.getColumnModel().getColumn(0).setPreferredWidth(10);			
		table.getColumnModel().getColumn(1).setPreferredWidth(480);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);		
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);

		boolean ativo = Boolean.parseBoolean(table.getValueAt(row, 3).toString());
		if(column == 0 || column == 2){
			this.setHorizontalAlignment(SwingConstants.CENTER);
		}else{
			this.setHorizontalAlignment(SwingConstants.LEFT);
		}
		pintaDesativado(this, table, row, column, ativo, msg);
		return this;
	}
    
    private static void pintaDesativado(Component c, JTable table, int rowIndex, int column, Boolean ativo, String msg){
    	//se o registro estiver ativo, a seleção terá a fonte e cor alterada na tabela
    	if (!ativo && !table.isCellSelected(rowIndex, column)) {  
    		c.setForeground(Color.RED);  
    		table.setToolTipText(msg);
    	}else if (!ativo && table.isCellSelected(rowIndex, column)) {  
    		c.setForeground(Color.RED);  
    		table.setToolTipText(msg);
    		c.setFont(new Font("Tahoma", Font.BOLD, 11));
    	} else if (ativo && table.isCellSelected(rowIndex, column)) {  
    		c.setForeground(Color.WHITE);  
    		c.setFont(new Font("Tahoma", Font.BOLD, 11));
    		table.setToolTipText(null);
    	}else {  	
    		c.setForeground(Color.BLACK);  
    		table.setToolTipText(null);         
    	} 
    }

}


