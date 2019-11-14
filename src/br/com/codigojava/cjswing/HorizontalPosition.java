package br.com.codigojava.cjswing;

public enum HorizontalPosition {
	CENTER(0), LEFT(2), RIGTH(4);

	private int position;

	private HorizontalPosition(int position) {
		this.position = position;
	}

	public int getValue() {
		return this.position;
	}
}
