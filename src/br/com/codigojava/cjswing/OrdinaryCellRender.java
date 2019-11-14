package br.com.codigojava.cjswing;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;


/**
 * Classe que trata o formato do conteudo Double a ser apresentado na celula de uma TableModel. 
 */
public class OrdinaryCellRender extends DefaultTableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5165267654399602724L;

	/**
	 * Metodo sobrescrito para mostrar o conteudo a ser apresentado de tipo Double no formato ordinario e alinhado ao centro na celula.
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		if(value instanceof Double) {  
            setText(value != null ? value+"º" : "");  
        }
		return this;
	}

}
