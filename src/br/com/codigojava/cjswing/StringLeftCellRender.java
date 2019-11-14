package br.com.codigojava.cjswing;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class StringLeftCellRender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 3146102832659403723L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);

		this.setHorizontalAlignment(SwingConstants.LEFT);
		
		Object val = table.getValueAt(row, column);
		// verifica se o valor é do tipo esperado
		if (val instanceof String) {
			setText(val != null ? ""+val : "");
		}
		return this;
	}
}
