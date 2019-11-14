package br.com.codigojava.cjswing;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CJTableModel<T> extends AbstractTableModel {
	private static final long serialVersionUID = 428871254034596102L;

	private CJTableColumn[] tableColumns; 
	private boolean[] editableColumns;
	private List<T> listItens;

	private boolean isSeriazizable;
	
	public CJTableModel(CJTableColumn[] tableColumns) {
		this.tableColumns = tableColumns;
		this.listItens = new ArrayList<>();
		this.editableColumns = new boolean[tableColumns.length];
	}

	public CJTableModel(CJTableColumn[] tableColumns, List<T> listItens) {
		this.tableColumns = tableColumns;
		this.listItens = new ArrayList<>(listItens);
		this.editableColumns = new boolean[tableColumns.length];
	}

	@Override
	public int getColumnCount() {
		return tableColumns.length;
	}

	@Override
	public int getRowCount() {
		return listItens.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return tableColumns[columnIndex].getName();
	}

	/** Este metodo deve verificar o indice recebido por parametro e 
	 * retornar o tipo de classe correspondente a coluna. */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		try {
			return (Class<?>) tableColumns[columnIndex].getType();
		} catch (Exception e) {
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return editableColumns[columnIndex];
	}

	/** Metodo que define se a celula sera editavel ou nao*/
	public void setEditableColumn(int columnIndex, boolean isEditable) {
		this.editableColumns[columnIndex] = isEditable;
	}
	
	/** Metodo que retornar o conteudo da celula especificada. 
	 * Primeiro obtemos o objeto referente a linha e em seguida 
	 * verificamos o indice da coluna para recuperar o campo correspondente. */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			T t = listItens.get(rowIndex);
			Field[] fields = t.getClass().getDeclaredFields();
			
			//necessario pois usando Reflections o getDeclaredFields obtem todos os atributos da classe
			if (isSeriazizable){
				fields = removeSerialVersionUID(fields);
			}
			
			Field field = fields[columnIndex];
			field.setAccessible(true);
			return field.get(t);
		} catch (Exception e) {
			throw new IndexOutOfBoundsException("getValueAt: columnIndex out of bounds");
		}
	}
	
	/** Metodo para informar se a classe <T> do objeto do model implements Serializable*/
	public boolean isSeriazizable(boolean isSeriazizable) {
		return this.isSeriazizable = isSeriazizable;
	}

	/** Metodo para remover/ignorar o atributo serialVersionUID de classes 
	 * de objetos que implementam Serializable */
	private Field[] removeSerialVersionUID(Field[] fields) {
	    if (fields != null) {
	        List<Field> listFields = new ArrayList<>(Arrays.asList(fields));
	        for (int i = 0; i < listFields.size(); i++) {
	            if (listFields.get(i).getName().equalsIgnoreCase("serialVersionUID")) {
	                listFields.remove(i);
	            }
	        }
	        return listFields.toArray(new Field[0]);
	    } else {
	        return new Field[0];
	    }
	}
	
	/** A implementacao deste metodo possui uma estrutura parecida com a do 
	  * getValueAt, porem devemos setar o valor do campo ao inves de retorna-lo. 
	  * Alem disso, precisamos passar para os 'listenners' que a celula em questao foi
	 alterada (fireTableCellUpdated), caso contrario o novo valor nao sera exibido na tabela. */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		// Pega o objeto referente a linha especificada.
		T t = listItens.get(rowIndex);
		try {
			Field[] fields = t.getClass().getDeclaredFields();
			
			//necessario pois usando Reflections o getDeclaredFields obtem todos os atributos da classe
			if (isSeriazizable){
				fields = removeSerialVersionUID(fields);
			}
			
			Field field = fields[columnIndex];
			field.setAccessible(true);
			field.set(t, value);
			//Notifica alteracao da celula
			fireTableCellUpdated(rowIndex, columnIndex); 
		} catch (Exception e) {
			throw new IndexOutOfBoundsException("setValueAt: columnIndex out of bounds");
		}
	}

	/** Retorna o objeto referente a linha especificada */
	public T getObject(int rowIndex) {
		return listItens.get(rowIndex);
	}

	/** Adiciona o objeto  especificado a lista do model */
	public void addObject(T t) {
		// Adiciona o registro.
		listItens.add(t);

		// Pega a quantidade de registros e subtrai 1 para
		// achar o ultimo indice. A subtracao e necessaria
		// porque os indices comecam em zero.
		int lastIndex = getRowCount() - 1;

		// Notifica a inserção.
		fireTableRowsInserted(lastIndex, lastIndex);
	}

	/** Remove o objeto da linha especificada. */
	public void removeObject(T t) {
		int index = indexObject(t);
		listItens.remove(index);
		// Notifica a remoção.
		fireTableRowsDeleted(index, index);
	}

	/** Remove o objeto  da linha especificada. */
	public void removeObject(int rowIndex) {
		// Remove o registro.
		listItens.remove(rowIndex);

		// Notifica a mudanca.
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	/** Adiciona uma lista de objetos ao final da lista do model. */
	public void addListObject(List<T> listObject) {
		// Adiciona os registros.
		listItens.addAll(listObject);
		fireTableDataChanged();
	}

	/** Retorna a lista de objetos do model*/
	public List<T> getListItens() {
		return listItens;
	}

	
	/** Remove todos os registros. */
	public void clear() {
		// Remove todos os elementos da lista de objetos .
		listItens.clear();

		// Notifica a mudanca.
		fireTableDataChanged();
	}

	private int indexObject(T t) {
		for (int i = 0; i < listItens.size(); i++) {
			if (t.equals(listItens.get(i))) {
				return i;
			}
		}
		return -1;
	}

}
