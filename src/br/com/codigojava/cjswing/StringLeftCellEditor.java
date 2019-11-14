package br.com.codigojava.cjswing;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

public class StringLeftCellEditor extends AbstractCellEditor implements TableCellEditor{

	private static final long serialVersionUID = 3993232072666467407L;
	
	private JTextField field;

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		Object val = table.getValueAt(row, column);
		if(value instanceof String){
			field = new JTextField(val.toString());
			field.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return field;
	}

	@Override
	public Object getCellEditorValue() {
		return field.getText();
	}
	
}
