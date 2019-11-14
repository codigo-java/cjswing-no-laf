package br.com.codigojava.cjswing;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class CJFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public CJFrame(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(CJFrame.class.getResource("/img/icone.png")));
	}
}
