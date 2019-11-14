package br.com.codigojava.cjswing;

import java.lang.reflect.Type;

public class CJTableColumn {

	private String name;
	private String atributeName;
	private Type type;
	
	public CJTableColumn(String name, Type type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAtributeName() {
		return this.atributeName;
	}
	
	public Type getType() {
		return this.type;
	}
}