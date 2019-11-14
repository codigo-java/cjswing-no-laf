package br.com.codigojava.cjswing;

import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

public class DoubleCellEditor extends AbstractCellEditor implements TableCellEditor{

	private static final long serialVersionUID = 3993232072666467407L;
	
	static int alignment = SwingConstants.RIGHT;
	
	private JTextField field;
	DecimalFormat nf = new DecimalFormat("#,##0.00");

	/**
	 * Método sobrescrito para mostrar o conteúdo a ser editado de tipo Double alinhado ao centro na célula.
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		Object val = table.getValueAt(row, column);
		String valor = nf.format(val);
		if(value instanceof Double){
			field = new JTextField(valor.toString());
			field.setHorizontalAlignment(alignment);
        }
        return field;
	}
	
	/**
	 * Método sobrescrito para mostrar o conteúdo a ser editado (tipo Double) em um formato brasileiro. Ex.: de 100.0 para 100,00
	 */
	@Override
	public Object getCellEditorValue() {
		Double valorDouble = Double.parseDouble(field.getText().replaceAll("\\.","").replace(",", ".")); 
		return valorDouble;
	}
	
}
