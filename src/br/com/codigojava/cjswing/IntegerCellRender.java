package br.com.codigojava.cjswing;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.commons.lang3.StringUtils;

public class IntegerCellRender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 2726688166112588842L;
	
	private int qtdZeroLeft;
	private HorizontalPosition position;
	
	public IntegerCellRender(int qtdZeroLeft, HorizontalPosition horizontalPosition) {
		this.qtdZeroLeft = qtdZeroLeft;
		this.position = horizontalPosition;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);
		this.setHorizontalAlignment(position.getValue());
		if(value instanceof Integer) {  
            setText(value != null ? StringUtils.leftPad(String.valueOf(value), qtdZeroLeft, "0") : "");  
        }
		return this;
	}
	
}
