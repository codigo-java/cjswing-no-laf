package br.com.codigojava.cjswing;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

public class BooleanCellEditor extends AbstractCellEditor implements TableCellEditor{

	private static final long serialVersionUID = 3993232072666467407L;
	
	private JCheckBox field;

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		Object val = table.getValueAt(row, column);
		if(value instanceof Boolean){
			field = new JCheckBox();
			field.setSelected((Boolean) val);
			field.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return field;
	}
	
	@Override
	public Object getCellEditorValue() {
		return field.isSelected();
	}
	
}
