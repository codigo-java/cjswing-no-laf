package br.com.codigojava.cjswing;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class LongZeroLeftCellRender extends DefaultTableCellRenderer {

	private int zeroLeft;
	private HorizontalPosition position;
	
	public LongZeroLeftCellRender(int qtdZeroLeft, HorizontalPosition horizontalPosition) {
		this.zeroLeft = qtdZeroLeft;
		this.position = horizontalPosition;
	}
	
	private static final long serialVersionUID = 2726688166112588842L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);
		this.setHorizontalAlignment(position.getValue());
		if(value instanceof Long) {  
            setText(value != null ? String.format("%0"+zeroLeft+"d", value) : "");  
        }
		return this;
	}

}
