package br.com.codigojava.cjswing;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class CJDialog extends JDialog {
	public CJDialog(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(CJFrame.class.getResource("/img/icone.png")));
		this.setBounds(50, 50, 497, 300);
		this.setResizable(false);
		this.setModal(true); //MANTER O FOCO NA JANELA
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);	
		this.setLocationRelativeTo(null); //CENTRALIZAR FRAME
		this.setFocusableWindowState(true);
	}

}
