package br.com.codigojava.cjswing;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class CJDialogMessage extends JDialog implements WindowListener{

	private static final long serialVersionUID = 9216349891745125965L;
	private static CJDialogMessage optionPane;
	private JTextArea textArea;
	private JButton okButton;

	public static CJDialogMessage create(String title, String message){
		if(optionPane == null){
			optionPane = new CJDialogMessage(title, message);
			optionPane.setLocationRelativeTo(null);
			optionPane.setVisible(true);
			//optionPane.setModal(true);
		}else{
			optionPane.toFront();
		}
		return optionPane;
	}
	public static CJDialogMessage create(String title, String message, Image imageIcon){
		if(optionPane == null){
			optionPane = new CJDialogMessage(title, message);
			optionPane.setIconImage(imageIcon);
			optionPane.setLocationRelativeTo(null);
			optionPane.setVisible(true);
			//optionPane.setModal(true);
		}else{
			optionPane.toFront();
		}
		return optionPane;
	}
	//METODO QUE DESTROI UMA INSTANCIA DESTA CLASSE
	public static CJDialogMessage destroy(){
		if(optionPane != null){
			optionPane.setVisible(false);
			optionPane = null;
		}
		return optionPane;
	}
	/**
	 * Create the dialog.
	 */
	public CJDialogMessage(String title, String message) {
		setModal(true);
		setTitle(title);
		addWindowListener(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CJDialogMessage.class.getResource("/img/icone.png")));
		setSize(420, 200);
		//insere no InputMap o comando FECHA para a tecla ESC
        this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true),"FECHA");  
        //no ActionMap insere o comando FECHA do InputMap e cria uma acao para ele
        this.getRootPane().getActionMap().put("FECHA", new AbstractAction() {  
			private static final long serialVersionUID = -7898802343052218682L;
			@Override
			public void actionPerformed(ActionEvent e) {  
        		destroy(); 
	        }  
        });
		{
			okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent paramActionEvent) {
					CJDialogMessage.destroy();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
						.addComponent(okButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(okButton)
					.addContainerGap())
		);
		{
			textArea = new JTextArea();
			scrollPane.setViewportView(textArea);
			textArea.setBackground(UIManager.getColor("Panel.background"));
			textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setEditable(false);
			textArea.setText(message);
		}
		getContentPane().setLayout(groupLayout);
	}
	
	@Override
	public void windowOpened(WindowEvent paramWindowEvent) {}
	@Override
	public void windowClosing(WindowEvent paramWindowEvent) {
		CJDialogMessage.destroy();		
	}
	@Override
	public void windowClosed(WindowEvent paramWindowEvent) {
		CJDialogMessage.destroy();
	}
	@Override
	public void windowIconified(WindowEvent paramWindowEvent) {}
	@Override
	public void windowDeiconified(WindowEvent paramWindowEvent) {}
	@Override
	public void windowActivated(WindowEvent paramWindowEvent) {}
	@Override
	public void windowDeactivated(WindowEvent paramWindowEvent) {}
}
