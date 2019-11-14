package br.com.codigojava.cjswing;

import java.awt.Component;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.toedter.calendar.JDateChooser;

public class DateCellEditor extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 3993232072666467407L;

	private JDateChooser field;

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		Object val = table.getValueAt(row, column);
		if (value != null) {
			if (value instanceof Date) {
				field = new JDateChooser();
				field.setDate((Date) val);
			} else if (value instanceof Calendar) {
				field = new JDateChooser();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime((Date) val);
				field.setDate(calendar.getTime());
			}
		}
		return field;
	}

	@Override
	public Object getCellEditorValue() {
		return field.getDate();
	}

}
