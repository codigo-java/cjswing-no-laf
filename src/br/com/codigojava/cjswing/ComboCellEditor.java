package br.com.codigojava.cjswing;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ComboCellEditor extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 3993232072666467407L;

	public ComboCellEditor(List<String> itens) {
		this.itens = itens.toArray(new String[0]);
	}

	private JComboBox<String> field;
	private String[] itens;

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		Object val = table.getValueAt(row, column);
		if (value instanceof String) {
			field = new JComboBox<>(itens);
			field.setSelectedItem(val.toString());
			field.setEditable(false);
			field.addItemListener(e -> {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					fireEditingStopped();
				}
			});
		}
		return field;
	}

	@Override
	public Object getCellEditorValue() {
		return field.getSelectedItem();
	}
}
