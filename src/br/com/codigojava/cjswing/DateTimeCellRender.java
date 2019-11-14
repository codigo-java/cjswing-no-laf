package br.com.codigojava.cjswing;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class DateTimeCellRender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -3263954196699689925L;

	static String pattern = "dd/MM/yyyy HH:mm:ss";
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		if(value != null) {  
			if(value instanceof Calendar){
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	            Calendar data = (Calendar) value;  
	            setText(sdf.format(data.getTime()));
			}else if(value instanceof Date){
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				Date date = (Date) value;  
	            setText(sdf.format(date.getTime()));
			}
        }
		return this;
	}
}
