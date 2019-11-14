package br.com.codigojava.cjswing;

import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class PercentageCellRender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -9146764755507582348L;

	/**
	 * Metodo sobrescrito para mostrar o conteudo a ser apresentado de tipo Double no formato de porcetagem alinhado ao centro na celula.
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);

		this.setHorizontalAlignment(SwingConstants.CENTER);
		
		Object val = table.getValueAt(row, column);
		// verifica se o valor � do tipo esperado
		if (val instanceof Double) {
			// faz o casting para BigDecimal
			Double valor = (Double) val;
			// formata o valor e seta como o texto do renderer
			DecimalFormat nf = new DecimalFormat("#,##0.00");
			setText(valor != null ? nf.format(valor)+"%" : "");
		}
		return this;
	}
}
