package br.com.codigojava.cjswing;

import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class CJButton extends JButton{
	
	public CJButton(){
		super();
	}	
	public CJButton(CJButtonType type){
		super();
		switch (type) {
		case NEW:
			setIcon(new ImageIcon(getClass().getResource("/img/document-new.png")));
			break;
		case CANCEL:
			setIcon(new ImageIcon(getClass().getResource("/img/cancel.png")));			
			break;
		case CLOSE:
			setIcon(new ImageIcon(getClass().getResource("/img/system-log-out.png")));			
			break;
		case EDIT:
			setIcon(new ImageIcon(getClass().getResource("/img/accessories-text-editor.png")));
			break;
		case DELETE:
			setIcon(new ImageIcon(getClass().getResource("/img/user-trash.png")));			
			break;
		case SAVE:
			setIcon(new ImageIcon(getClass().getResource("/img/media-floppy.png")));			
			break;
		default:
			break;
		}
	}	
	public CJButton(CJButtonType type, String texto){
		super();
		setText(texto);
	}	
	public CJButton(CJButtonType type, String texto, String toolTipText){
		super();
		setText(texto);
		setToolTipText(toolTipText);
	}	
	public CJButton(CJButtonType type, String texto, String toolTipText, Icon icon){
		super();
		setText(texto);
		setToolTipText(toolTipText);
		setIcon(icon);
	}	
	public CJButton(CJButtonType type, String texto, String toolTipText, Icon icon, String comando){
		super();
		setText(texto);
		setToolTipText(toolTipText);
		setIcon(icon);
		setActionCommand(comando);
	}
	public CJButton(CJButtonType type, String texto, String toolTipText, Icon icon, String comando, ActionListener listener){
		super();
		setText(texto);
		setToolTipText(toolTipText);
		setIcon(icon);
		setActionCommand(comando);
		addActionListener(listener);
	}
	public CJButton(CJButtonType type, String texto, Icon icon, String comando, ActionListener listener){
		super();
		setText(texto);
		setIcon(icon);
		setActionCommand(comando);
		addActionListener(listener);
	}
}