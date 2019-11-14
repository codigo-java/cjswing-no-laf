package br.com.codigojava.cjswing;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class BooleanToStringCellRender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 3146102832659403723L;

	static String textTrue = "Sim";
	static String textFalse = "Não";
	
	/**
	 * Metodo sobrescrito para mostrar o conteudo a ser apresentado de tipo String alinhado ao centro na celula.
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);

		this.setHorizontalAlignment(SwingConstants.CENTER);
		
		Object val = table.getValueAt(row, column);
		if (val instanceof Boolean) {
			if((Boolean)val == true)
				setText(textTrue);
			else
				setText(textFalse);
		}
		return this;
	}
}
