package br.com.codigojava.cjswing;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class BooleanCellRender extends JCheckBox implements TableCellRenderer {

	private static final long serialVersionUID = 3146102832659403723L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		this.setHorizontalAlignment(SwingConstants.CENTER);
		
		Object val = table.getValueAt(row, column);
		if (val instanceof Boolean) {
            setSelected((value != null && ((Boolean) value).booleanValue()));
		}
		return this;
	}
}
