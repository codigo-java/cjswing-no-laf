package br.com.codigojava.cjswing;

import java.awt.Component;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CurrencyCellRender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 3146102832659403723L;

	private Locale locale;
	private NumberFormat format;

	public CurrencyCellRender() {
		this.locale = Locale.getDefault();
		format = NumberFormat.getCurrencyInstance(this.getLocale());
	}

	public CurrencyCellRender(Locale locale) {
		this.locale = locale;
		format = NumberFormat.getCurrencyInstance(this.getLocale());
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Metodo sobrescrito para mostrar o conteudo a ser apresentado de tipo Double
	 * no formato de valor em R$ e alinhado a direita na celula.
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		this.setHorizontalAlignment(SwingConstants.CENTER);

		Object val = table.getValueAt(row, column);
		// verifica se o valor eh do tipo esperado
		if (val instanceof Double) {
			// faz o casting para Double
			Double valor = (Double) val;
			setText(format.format(valor));
		} else if (val instanceof BigDecimal) {
			// faz o casting para BigDecimal
			BigDecimal valor = (BigDecimal) val;
			setText(format.format(valor));
		}

		return this;
	}
}
